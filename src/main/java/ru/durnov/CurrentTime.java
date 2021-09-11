package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentTime implements Time {
    private final static Logger logger = LogManager.getLogger(CurrentTime.class);
    private LocalDateTime lastReportTime;
    private final static Pattern pattern = Pattern.compile("\"DateTime=([12][09][0-9][0-9])/([0-1][0-9])/([0-3][0-9])(.)+$\"");
    private boolean mustSend;


    @Override
    public boolean checkTimeWriting(String timeString) {
        Matcher matcher = pattern.matcher(timeString);
        if (matcher.matches()){
            try {
                LocalDateTime timeFromString = LocalDateTime.parse(timeString);
                if (lastReportTime.compareTo(timeFromString) > 0){
                    lastReportTime = timeFromString;
                    mustSend = true;
                }
            } catch (Exception e) {
                logger.error("Ошибка при парсинге строки " + timeString);
            }
        }
        return mustSend;
    }

    @Override
    public void reset() {
        mustSend = false;
    }


}
