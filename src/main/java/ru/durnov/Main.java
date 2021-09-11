package ru.durnov;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws IOException, MqttException, InterruptedException {
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("config.txt")));
        Log log = new CurrentDayLog();
        String publisherId = UUID.randomUUID().toString();
        Time time = new CurrentTime();
        int interval;
        try {
            interval = Integer.parseInt(properties.getProperty("interval"));
        } catch (NumberFormatException e) {
            interval = 15;
        }
        Publisher publisher = new Publisher(
                properties.getProperty("broker_address"),
                publisherId,
                interval,
                properties.getProperty("topic")
        );
        while (true){
            List<String> stringList = log.stringList();
            for (String s : stringList) {
                if (time.checkTimeWriting(s)){
                    publisher.sendMessage(s);
                }
            }
            time.reset();
            Thread.sleep(interval);
        }

    }
}
