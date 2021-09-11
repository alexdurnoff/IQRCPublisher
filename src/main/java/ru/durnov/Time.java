package ru.durnov;

public interface Time {

    /**
     * Parse String value and set new time value if required;
     * @param timeString - String from LogFile.
     * @return true if time value from String is more than previous time value.
     */
    boolean checkTimeWriting(String timeString);

    /**
     * reset after reading log file.
     */
    void reset();
}
