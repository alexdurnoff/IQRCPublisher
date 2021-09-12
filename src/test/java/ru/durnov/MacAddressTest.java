package ru.durnov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MacAddressTest {

    @Test
    void address() {
        String str = "i=003, MAC=0XA0E25A022000009C, NWK=0X5121, P=000, M=003, S=0XD0, F=0X01, ES=0XBC14";
        String result = new MacAddress(str).address();
        assertEquals(result, "MAC=0XA0E25A022000009C");
    }
}