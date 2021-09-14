package ru.durnov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CurrentDayLog implements Log {
    private final String pathToFile = "/home/pi/iqrclog/currentday.log";

    @Override
    public List<String> stringList() throws IOException {
        return Files.readAllLines(Paths.get(pathToFile));
    }
}
