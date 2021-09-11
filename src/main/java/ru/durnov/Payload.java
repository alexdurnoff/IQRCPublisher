package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Encapsulate String from log file and return from single method payload for MQTTMessage as byte array;
 */
public class Payload {
    private final String stringFromFile;
    private final static Logger logger = LogManager.getLogger(Payload.class);

    public Payload(String stringFromFile) {
        this.stringFromFile = stringFromFile;
    }

    public byte[] payload() {
        //TODO дописать метод
        return new byte[0];
    }
}
