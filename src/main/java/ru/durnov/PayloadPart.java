package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Encapsulate String from log file and return from single method payload for MQTTMessage as byte array;
 */
public class PayloadPart {
    private final static Logger logger = LogManager.getLogger(PayloadPart.class);
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
    private final String macAddress;

    public PayloadPart(String stringFromFile) {
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
        this.macAddress = new MacAddress(stringFromFile).address();
    }

    public String payload() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(this.macAddress)
                .append(" ")
                .append("realtemp:")
                .append(" ")
                .append(realtemp)
                .append(" ")
                .append("settemp:")
                .append(" ")
                .append(settemp)
                .append(" ")
                .append("openstatus:")
                .append(" ")
                .append(openStatus)
                .append(" ")
                .append("heatSaving:")
                .append(" ")
                .append(heatSaving)
                .append(" ")
                .append("LQI:")
                .append(" ")
                .append(lqi)
                .append(" ")
                .append("Battery:")
                .append(" ")
                .append(battery)
                .append(" ")
                .append("Error:")
                .append(" ")
                .append(error)
                .append(" ")
                .append("ResponseTime:")
                .append(" ")
                .append(responseTime)
                .append(" ")
                .append("Fmode")
                .append(" ")
                .append(fmode);
        return stringBuilder.toString();
    }
}
