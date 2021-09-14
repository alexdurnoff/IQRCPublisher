package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Publisher extends MqttClient {
    private final String topic;
    private final static Logger logger = LogManager.getLogger(Publisher.class);

    public Publisher(String serverURI, String clientId, int interval, String topic) throws MqttException {
        super(serverURI, clientId);
        this.topic = topic;
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(interval/3);
        this.connect(options);
    }

    public Publisher(Properties properties, int interval) throws MqttException {
        super(properties.getProperty("broker_address"), String.valueOf( UUID.randomUUID()));
        this.topic = properties.getProperty("topic");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        if (properties.containsKey("password")){
            options.setUserName(properties.getProperty("user"));
            options.setPassword(properties.getProperty("password").toCharArray());
        }
        options.setConnectionTimeout(interval/3);
        this.connect(options);
    }

    public void sendMessage(String stringFromFile) throws MqttException {
        MqttMessage msg = new MqttMessage(stringFromFile.getBytes(StandardCharsets.UTF_8));
        msg.setQos(0);
        msg.setRetained(true);
        this.publish(topic, msg);
    }
}
