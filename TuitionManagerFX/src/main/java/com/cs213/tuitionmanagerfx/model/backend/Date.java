package com.cs213.tuitionmanagerfx.model.backend;

import com.cs213.tuitionmanagerfx.model.config.DateConfig;

import java.util.Calendar;

/**
 * A class implementing date.
 * Stores year, month, and day.
 *
 * @author Noor, Umar
 */
public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructor that parses a string date in the form "mm/dd/yyyy" and creates a Date object.
     *
     * @param date String representation of the date
     */
    public Date(String date) {
        String[] parts = date.split("/");

        year = Integer.parseInt(parts[DateConfig.YEAR_INDEX]);
        month = Integer.parseInt(parts[DateConfig.MONTH_INDEX]);
        day = Integer.parseInt(parts[DateConfig.DAY_INDEX]);
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
        if (year < DateConfig.MINIMUM_YEAR || year > currentYear) {
            return false;
        }

        // check if month is valid
        if (month < DateConfig.JANUARY || month > DateConfig.DECEMBER ||
                (year == currentYear && month > currentMonth)) {
            return false;
        }

        // check if day is valid
        if (day < 1 ||
                (isLongMonth(month) && day > DateConfig.LONG_MONTHS_LENGTH) ||
                (isShortMonth(month) && day > DateConfig.SHORT_MONTHS_LENGTH)) {
            return false;
        }

        //  check if day is valid during feb and leap year
        if (month == DateConfig.FEBRUARY) {
            if (isLeapYear(year)) {
                if (day > DateConfig.FEB_LEAP_LENGTH) return false;
            } else {
                if (day > DateConfig.FEB_NORMAL_LENGTH) return false;
            }
        }

        return year != currentYear || month != currentMonth || day <= currentDay;
    }

    /**
     * Helper method that checks if a month has 31 days
     *
     * @param month the month to check
     * @return true if long month, false otherwise
     */
    private boolean isLongMonth(int month) {
        for (int m : DateConfig.LONG_MONTHS) {
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
        for (int m : DateConfig.SHORT_MONTHS) {
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
        if (year % DateConfig.QUADRENNIAL == 0) {
            if (year % DateConfig.CENTENNIAL == 0) {
                return year % DateConfig.QUARTERCENTENNIAL == 0;
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
        if (month + day + year == 0) {
            return "--/--/--";
        }
        return month + "/" + day + "/" + year;
    }
}