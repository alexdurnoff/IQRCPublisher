package ru.durnov;

import java.util.List;

public class CurrentDayLog implements Log {
    private final String address;
    private final String user;
    private final String password;


    public CurrentDayLog(String address, String user, String password) {
        this.address = address;
        this.user = user;
        this.password = password;
    }


    @Override
    public List<String> stringList() {
        return null;
    }
}
