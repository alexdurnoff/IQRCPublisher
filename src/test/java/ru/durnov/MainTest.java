package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

public class MainTest {

    @Test
    public void testApplication() throws IOException, MqttException, InterruptedException {
        /*Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("config.txt")));
        String publisherId = UUID.randomUUID().toString();
        IMqttClient subscriber = new MqttClient(properties.getProperty("broker_address"), publisherId);
        subscriber.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connection lost " + cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("message received " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                try {
                    System.out.println(token.getMessage());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
        subscriber.connect();
        subscriber.subscribe(properties.getProperty("topic"));
        Main.main(new String[]{""});*/
    }
}
