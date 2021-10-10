package studentmanager.config;

/**
 * A class containing constants for use with the Date class.
 * Can be used to configure custom values for different calendars.
 *
 * @author Noor, Umar
 */
public class DateConfig {

    public static final int MONTH_INDEX = 0;
    public static final int DAY_INDEX = 1;
    public static final int YEAR_INDEX = 2;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int MINIMUM_YEAR = 2021;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public static final int[] LONG_MONTHS = new int[]{JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER};
    public static final int[] SHORT_MONTHS = new int[]{APRIL, JUNE, SEPTEMBER, NOVEMBER};

    public static final int LONG_MONTHS_LENGTH = 31;
    public static final int SHORT_MONTHS_LENGTH = 30;
    public static final int FEB_NORMAL_LENGTH = 28;
    public static final int FEB_LEAP_LENGTH = 29;
}
