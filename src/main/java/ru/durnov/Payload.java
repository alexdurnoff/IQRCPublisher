package ru.durnov;

/**
 * Encapsulate String from log file and return from single method payload for MQTTMessage as byte array;
 */
public class Payload {
    private final String stringFromFile;

    public Payload(String stringFromFile) {
        this.stringFromFile = stringFromFile;
    }

    public byte[] payload() {
        //TODO дописать метод
        return new byte[0];
    }
}
