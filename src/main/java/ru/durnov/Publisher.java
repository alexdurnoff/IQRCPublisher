package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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

    public void sendMessage(String stringFromFile) throws MqttException {
        logger.info("publisher detect string from log for sending message");
        MqttMessage msg = new MqttMessage(stringFromFile.getBytes(StandardCharsets.UTF_8));
        msg.setQos(0);
        msg.setRetained(true);
        this.publish(topic, msg);
    }

    public void sendTimeMessage(LocalDateTime timeFromString) throws MqttException {
        MqttMessage msg = new MqttMessage(("DateTime = " + timeFromString.toString().replace("T", "")).getBytes(StandardCharsets.UTF_8));
        msg.setQos(0);
        msg.setRetained(true);
        this.publish(topic, msg);
    }
}
