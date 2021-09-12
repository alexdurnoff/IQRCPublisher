package ru.durnov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMatcherTest {

    @Test
    void checkString() {
        PublisherMatcher publisherMatcher = new PublisherMatcher("i=001, MAC=0XA0E25A02200000A0, NWK=0XA241, P=000, M=001, S=0XD0, F=0X01, ES=0XB416");
        assertTrue(publisherMatcher.checkString());
    }
}