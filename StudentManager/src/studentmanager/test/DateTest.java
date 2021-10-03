package studentmanager.test;

import studentmanager.Date;

import java.util.Calendar;

public class DateTest {
    /**
     * Test-bed main for unit testing isValid method
     * TODO: convert to junit
     *
     * @param args
     */
    public static void main (String args[]) {

        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);

        // Test case 1
        Date date = new Date("11/21/800");
        boolean expectedResult = false;
        boolean result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 1 passed");
        }
        else {
            System.out.println("Test case 1 failed.");
        }

        // Test case 2
        date = new Date("11/21/" + String.valueOf(year + 1));
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 2 passed");
        }
        else {
            System.out.println("Test case 2 failed.");
        }

        // Test case 3
        date = new Date("13/21/1981");
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 3 passed");
        }
        else {
            System.out.println("Test case 3 failed.");
        }

        // Test case 4
        date = new Date("-1/21/1981");
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 4 passed");
        }
        else {
            System.out.println("Test case 4 failed.");
        }

        // Test case 5
        date = new Date("2/29/2018");
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 5 passed");
        }
        else {
            System.out.println("Test case 5 failed.");
        }

        // Test case 6
        date = new Date("2/30/2020");
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 6 passed");
        }
        else {
            System.out.println("Test case 6 failed.");
        }

        // Test case 7
        date = new Date("11/0/1980");
        expectedResult = false;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 7 passed");
        }
        else {
            System.out.println("Test case 7 failed.");
        }

        // Test case 8
        int [] longMonths = new int[] {1,3,5,7,8,10,12};
        boolean passed = true;
        for (int longMonth : longMonths) {
            date = new Date(longMonth + "/32/2020");
            expectedResult = false;
            result = date.isValid();
            if (result != expectedResult) {
                passed = false;
            }
        }
        if (passed) {
            System.out.println("Test case 8 passed");
        }
        else {
            System.out.println("Test case 8 failed.");
        }

        // Test case 9
        int [] shortMonths = new int[] {4,6,9,11};
        passed = true;
        for (int shortMonth : shortMonths) {
            date = new Date(shortMonth + "/32/2020");
            expectedResult = false;
            result = date.isValid();
            if (result != expectedResult) {
                passed = false;
            }
        }
        if (passed) {
            System.out.println("Test case 9 passed");
        }
        else {
            System.out.println("Test case 9 failed.");
        }

        // test case 10
        date = new Date("6/5/2000");
        expectedResult = true;
        result = date.isValid();
        if (result == expectedResult) {
            System.out.println("Test case 10 passed");
        }
        else {
            System.out.println("Test case 10 failed.");
        }
    }
}
