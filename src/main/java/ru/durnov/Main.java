package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get("config.txt")));
        } catch (IOException e) {
           logger.error("error parsing config.txt " + e);
           System.exit(-1);
        }
        Log log = new CurrentDayLog();
        int interval;
        try {
            interval = Integer.parseInt(properties.getProperty("interval"));
        } catch (NumberFormatException e) {
            logger.error("error parsing interval: " + properties.getProperty("interval") + " " + e);
            interval = 15;
        }
        Publisher publisher = null;
        try {
            publisher = new Publisher(properties, interval);
        } catch (MqttException e) {
            logger.error("exception from construct MQTT-client " + e);
            System.exit(-1);
        }
        Time time = new CurrentTime();
        while (true){
            try {
                List<String> stringList = log.stringList();
                for (String s : stringList) {
                    if (time.checkTimeWriting(s) && new PublisherMatcher(s).checkString()) {
                        try {
                            String message = time.time() + " " + new PayloadPart(s).payload();
                            publisher.sendMessage(message);
                            logger.info("sending message \n" + message);
                        } catch (MqttException e) {
                            logger.error("exception from sending message " + e);
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("exception from reading current log file " + e);
            }
            time.reset();
            Thread.sleep(interval* 1000L);
        }
    }
}
