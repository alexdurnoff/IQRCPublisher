package ru.durnov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CurrentDayLog implements Log {
    private final String pathToFile = "/home/pi/iqrclog/currentday.log.gz";
    private final String resultFilePath = "/home/pi/iqrclog/currentday.log";

    @Override
    public List<String> stringList() throws IOException {
        GUnzipper.unzipFile(pathToFile);
        Path path = Paths.get(resultFilePath);
        List<String> stringList = Files.readAllLines(path);
        Files.delete(path);
        return stringList;
    }
}
