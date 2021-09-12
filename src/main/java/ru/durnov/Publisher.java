package ru.durnov;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Publisher extends MqttClient {
    private final static Pattern pattern = Pattern.compile("\"^i=([0-9]{3}), MAC=0XA0E25A([0-9ABCDEF]{10}), NWK=0X([0-9ABCDEF]{4}), P=([0-9]{3,5}), M=([0-9]{3,5}), S=0X([0-9ABCDEF]{2}), F=0X([0-9ABCDEF]{2}), ES=0X([0-9ABCDEF]{4})$\"");
    private final String topic;

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
        Matcher matcher = pattern.matcher(stringFromFile);
        if (matcher.matches()){
            MqttMessage msg = new MqttMessage(new Payload(stringFromFile).payload());
            msg.setQos(0);
            msg.setRetained(true);
            this.publish(topic, msg);
        }

    }

    public void sendTimeMessage(LocalDateTime timeFromString) throws MqttException {
        MqttMessage msg = new MqttMessage(("DateTime = " + timeFromString.toString().replace("T", "")).getBytes(StandardCharsets.UTF_8));
        msg.setQos(0);
        msg.setRetained(true);
        this.publish(topic, msg);
    }
}
