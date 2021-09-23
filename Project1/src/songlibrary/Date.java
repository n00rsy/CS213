package songlibrary;

import java.util.Calendar;

/**
 * A class implementing date.
 * Stores year, month, and day.
 *
 * @author Noor
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor that parses a string date in the form “mm/dd/yyyy” and creates a Date object.
     *
     * @param date String representation of the date
     */
    public Date(String date) {
        String[] parts = date.split("/");

        year = Integer.parseInt(parts[Constants.YEAR_INDEX]);
        month = Integer.parseInt(parts[Constants.MONTH_INDEX]);
        day = Integer.parseInt(parts[Constants.DAY_INDEX]);
    }

    /**
     * Constructor that creates a Date object with today's date.
     */
    public Date() {
        Calendar today = Calendar.getInstance();

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * Checks if the date is valid or not against a set of constraints.
     *
     * @return true if the date is valid, false otherwise.
     */
    public boolean isValid() {
        Calendar today = Calendar.getInstance();

        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH);
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        // check if year is valid
        if (year < Constants.THE_EIGHTYS || year > currentYear) {
            return false;
        }

        // check if month is valid
        if (month < Constants.JANUARY || month > Constants.DECEMBER ||
                (year == currentYear && month > currentMonth)) {
            return false;
        }

        // check if day is valid
        if (day < 1 ||
                (isLongMonth(month) && day > Constants.LONG_MONTHS_LENGTH) ||
                (isShortMonth(month) && day > Constants.SHORT_MONTHS_LENGTH)) {
            return false;
        }

        //  check if day is valid during feb and leap year
        if (month == Constants.FEBRUARY) {
            if (isLeapYear(year)) {
                if (day > Constants.FEB_LEAP_LENGTH) return false;
            } else {
                if (day > Constants.FEB_NORMAL_LENGTH) return false;
            }
        }

        if ((year == currentYear && month == currentMonth && day > currentDay)) {
            return false;
        }
        return true;
    }

    /**
     * Helper method that checks if a month has 31 days
     *
     * @param month the month to check
     * @return true if long month, false otherwise
     */
    private boolean isLongMonth(int month) {
        for (int m : Constants.LONG_MONTHS) {
            if (month == m) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method that checks if a month has 30 days
     *
     * @param month the month to check
     * @return true if short month, false otherwise
     */
    private boolean isShortMonth(int month) {
        for (int m : Constants.SHORT_MONTHS) {
            if (month == m) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method that checks if a year is a leap year or not
     *
     * @param year the year to check
     * @return true if leap year, false if otherwise
     */
    private boolean isLeapYear(int year) {
        if (year % Constants.QUADRENNIAL == 0) {
            if (year % Constants.CENTENNIAL == 0) {
                if (year % Constants.QUARTERCENTENNIAL == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Compares this date against another object.
     *
     * @param date
     * @return positive number if this date is greater, 0 if equal, negative if smaller
     */
    @Override
    public int compareTo(Date date) {

        if (year != date.year) return year - date.year;
        if (month != date.month) return month - date.month;
        if (day != date.day) return day - date.day;

        return 0;
    }

    /**
     * Returns the string representation of this date object.
     *
     * @return
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}