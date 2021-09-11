package ru.durnov;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesTestClass {

    @Test
    void testPropertiesFileFormat() throws IOException {
        Properties properties = new Properties();
        properties.put("ssh_address", "192.168.1.190");
        properties.put("ssh_user", "alexej");
        properties.put("ssh_password", "1103206bygh");
        properties.put("interval", "15");
        properties.put("topic", "TOPIC");
        properties.put("broker_address", "tcp://iot.eclipse.org:1883");
        properties.store(Files.newOutputStream(Paths.get("configTest.txt")), "Тестовый конфиг");
    }
}
