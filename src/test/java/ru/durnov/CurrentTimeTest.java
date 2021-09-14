package ru.durnov;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CurrentTimeTest {

    @Test
    void checkTimeWriting() throws IOException, MqttException {
        StringBuilder stringBuilder = new StringBuilder();
        CurrentTime currentTime = new CurrentTime();
        String str1 = "DateTime=2015/07/30 00:06:52   wday=4";
        assertTrue(currentTime.checkTimeWriting(str1));
        currentTime.reset();
        String str2 = "i=001, MAC=0XA0E25A02200000A0, NWK=0XA241, P=000, M=001, S=0XD0, F=0X01, ES=0XB416";
        assertFalse(currentTime.checkTimeWriting(str2));
    }
}