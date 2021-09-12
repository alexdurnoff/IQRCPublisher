package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/**
 * Encapsulate String from log file and return from single method payload for MQTTMessage as byte array;
 */
public class Payload {
    private final static Logger logger = LogManager.getLogger(Payload.class);
    private final double realtemp;
    private final double settemp;
    private final double deltaSetPoint;
    private final int openStatus;
    private final int extendedOpenValuePercent;
    private final int heatSaving;
    private final double lqi;
    private final int battery;
    private final int error;
    private final int responseTime;
    private final int fmode;

    public Payload(String stringFromFile) {
        double lqi1;
        this.realtemp = NumberConverter.getCurrentActualTemperature(stringFromFile);
        this.settemp = NumberConverter.getCurrentSetTemperature(stringFromFile);
        this.deltaSetPoint = this.realtemp - this.settemp;
        this.extendedOpenValuePercent = NumberConverter.getExtendedOpenStatus(stringFromFile);
        this.openStatus = NumberConverter.getOpenStatus(stringFromFile) + extendedOpenValuePercent;
        this.heatSaving = 100 - openStatus;
        this.battery = NumberConverter.getBatteryByString(stringFromFile);
        try {
            lqi1 = Double.parseDouble(NumberConverter.getLQIByString(stringFromFile));
        } catch (NumberFormatException e) {
            logger.error("error from parsing lqi string:" + stringFromFile);
            lqi1 = 0.;
        }
        this.lqi = lqi1;
        this.error = NumberConverter.getErrorsByString(stringFromFile);
        this.fmode = NumberConverter.getFModeByString(stringFromFile);
        this.responseTime = NumberConverter.getResponceTimeByString(stringFromFile);
    }

    public byte[] payload() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("realtemp:")
                .append(realtemp)
                .append("settemp:")
                .append(settemp)
                .append("openstatus:")
                .append(openStatus)
                .append("heatSaving:")
                .append(heatSaving)
                .append("LQI:")
                .append(lqi)
                .append("Battery:")
                .append(battery)
                .append("Error:")
                .append(error)
                .append("ResponseTime:")
                .append(responseTime)
                .append("Fmode")
                .append(fmode);
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
