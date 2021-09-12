package ru.durnov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для получения LocalDateTime из строки лога.
 */
public class LocalDateTimeFromLog {
    private final static Logger logger = LogManager.getLogger(LocalDateTimeFromLog.class);
    private final LocalDateTime localDateTime;
    private final static Pattern timePattern = Pattern.compile("^([12][0-9][0-9][0-9])/([01][0-9])/([0-3][0-9])\\s([0-2][0-9]):([0-5][0-9]):([0-5][0-9]).*$");

    public LocalDateTimeFromLog(String timeString){
        logger.info("parsing LocalDateTime from " + timeString);
        String time = timeString.substring(9,28);
        Matcher matcher = timePattern.matcher(time);
        if (matcher.matches()){
            Integer year = Integer.parseInt(matcher.group(1));
            Integer month = Integer.parseInt(matcher.group(2));
            Integer day = Integer.parseInt(matcher.group(3));
            Integer hour = Integer.parseInt(matcher.group(4));
            Integer minutes = Integer.parseInt(matcher.group(5));
            Integer seconds = Integer.parseInt(matcher.group(6));
            this.localDateTime = LocalDateTime.of(year,month,day,hour,minutes,seconds);
        } else {
            throw new IllegalStateException("wrong time string. Can't parsing");
        }
    }


    public LocalDateTime value() {
        return this.localDateTime;
    }
}
