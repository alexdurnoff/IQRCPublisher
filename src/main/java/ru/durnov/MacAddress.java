package ru.durnov;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAddress {
    private final static Pattern pattern = Pattern.compile("(MAC=0XA0E25A[0-9ABCDEF]{10})");
    private final String address;

    public MacAddress(String stringFromFile) {
        Matcher matcher = pattern.matcher(stringFromFile);
        if (!matcher.find()){
            throw new IllegalStateException("wrong string, can't parse mac-address");
        }
        this.address = matcher.group(1);
    }

    public String address() {
        return this.address;
    }
}
