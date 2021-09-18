package songlibrary;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public Date(String date) {
        String[] parts = date.split("/");

        year = Integer.parseInt(parts[Constants.YEAR_INDEX]);
        month = Integer.parseInt(parts[Constants.MONTH_INDEX]);
        day = Integer.parseInt(parts[Constants.DAY_INDEX]);
    } //take “mm/dd/yyyy” and create a Date object

    public Date() {
        Calendar today = Calendar.getInstance();

        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

    } //create an object with today’s date (see Calendar class)

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
            if(isLeapYear(year)) {
                if (day > Constants.FEB_LEAP_LENGTH) return false;
            }
            else {
                if (day > Constants.FEB_NORMAL_LENGTH) return false;
            }
        }

        if((year == currentYear && month == currentMonth && day > currentDay)) {
            return false;
        }
        return true;
    }

    private boolean isLongMonth(int month) {
        for (int m : Constants.LONG_MONTHS) {
            if (month == m) {
                return true;
            }
        }
        return false;
    }

    private boolean isShortMonth(int month) {
        for (int m : Constants.SHORT_MONTHS) {
            if (month == m) {
                return true;
            }
        }
        return false;
    }

    private boolean isLeapYear(int year) {
        if (year % Constants.QUADRENNIAL == 0) {
            if (year % Constants.CENTENNIAL == 0) {
                if(year % Constants.QUARTERCENTENNIAL == 0) {
                    return  true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {

        if (year != date.year) return year - date.year;
        if (month != date.month) return month - date.month;
        if (day != date.day) return day - date.day;

        return 0;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}