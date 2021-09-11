package ru.durnov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CurrentDayLog implements Log {
    private final String pathToFile = "/home/pi/iqrclog/currentday.log.gz";

    @Override
    public List<String> stringList() throws IOException {
        GUnzipper.unzipFile("/tmp/" + "currentday_log");
        Path path = Paths.get("/tmp/currentday_log");
        List<String> stringList = Files.readAllLines(path);
        Files.delete(path);
        return stringList;
    }
}
