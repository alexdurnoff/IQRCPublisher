package ru.durnov;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublisherMatcher {
    private final static Pattern pattern = Pattern.compile("^i=([0-9]{3}), MAC=0XA0E25A([0-9ABCDEF]{10}), NWK=0X([0-9ABCDEF]{4}), P=([0-9]{3,5}), M=([0-9]{3,5}), S=0X([0-9ABCDEF]{2}), F=0X([0-9ABCDEF]{2}), ES=0X([0-9ABCDEF]{4})$");
    private final Matcher matcher;

    public PublisherMatcher(String stringFromFile) {
        this.matcher = pattern.matcher(stringFromFile);
    }

    public boolean checkString() {
        return this.matcher.matches();
    }
}
