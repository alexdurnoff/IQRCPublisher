package ru.durnov;

import java.io.IOException;
import java.util.List;

public interface Log {
    List<String> stringList() throws IOException;
}
