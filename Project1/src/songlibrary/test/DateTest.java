package songlibrary.test;

import org.junit.Assert;
import org.junit.Test;
import songlibrary.models.Date;

import java.util.Calendar;

public class DateTest {

    private static String SMALL_YEAR = "11/21/800";
    Calendar today = Calendar.getInstance();
    int year = today.get(Calendar.YEAR);
    private static String RIGHT_YEAR = "11/21/" + String.valueOf(year + 1);
    private static String YEAR3 = "13/21/1981";
    private static String YEAR3a = "-1/21/1981";
    private static String notLEAPYEAR = "2/29/2018";
    private static String LEAPYEAR = "2/30/2020";
    private static String VALIDDAY = "11/0/1980";
    private static String VALID_DAY = "11/-5/1980";
    private static String ODD_MONTH = "1/32/2020";
    private static String ODD_MONTH_A = "3/32/2020";
    private static String ODD_MONTH_B = "5/32/2020";
    private static String ODD_MONTH_C = "7/32/2020";
    private static String ODD_MONTH_D = "8/32/2020";
    private static String ODD_MONTH_E = "10/32/2020";
    private static String ODD_MONTH_F = "12/32/2020";
    private static String EVEN_MONTH = "4/31/2020";
    private static String EVEN_MONTH_A = "6/31/2020";
    private static String EVEN_MONTH_B = "9/31/2020";
    private static String EVEN_MONTH_C = "11/31/2020";

    // Test 1
    @Test
    public void isValidMinDateTest() {
        Date date = new Date(SMALL_YEAR);
        Assert.assertFalse("Test case 1 failed",date.isValid());
        System.out.println("Test case 1 passed.");
    }

    //Test 2
    @Test
    public void isValidYear() {
        Date date = new Date(RIGHT_YEAR);
        Assert.assertFalse("Test case 2 failed", date.isValid());
        System.out.println("Test case 2 passed.");
    }

    //Test 3
    @Test
    public void isValidMonthRangeTest() {
        Date date = new Date(YEAR3);
        Date dateA = new Date(YEAR3a);
        Assert.assertFalse("Test case 3 failed", date.isValid());
        Assert.assertFalse("Test case 3 failed", dateA.isValid());
        System.out.println("Test case 3 passed.");
    }

    //Test 4
    @Test
    public void isNotLeapYear() {
        Date date = new Date(notLEAPYEAR);
        Assert.assertFalse("Test case 4 failed", date.isValid());
        System.out.println("Test case 4 passed.");
    }

    //Test 5
    @Test
    public void isLeapYear() {
        Date date = new Date(LEAPYEAR);
        Assert.assertFalse("Test case 5 failed", date.isValid());
        System.out.println("Test case 5 passed.");
    }

    //Test 6
    @Test
    public void isValidDay() {
        Date date = new Date(VALIDDAY);
        Date dateA = new Date(VALID_DAY);
        Assert.assertFalse("Test case 6 failed", date.isValid());
        Assert.assertFalse("Test case 6 failed", dateA.isValid());
        System.out.println("Test case 6 passed.");
    }

    //Test 7
    @Test
    public void isOddMonth() {
        Date date = new Date(ODD_MONTH);
        Date dateA = new Date(ODD_MONTH_A);
        Date dateB = new Date(ODD_MONTH_B);
        Date dateC = new Date(ODD_MONTH_C);
        Date dateD = new Date(ODD_MONTH_D);
        Date dateE = new Date(ODD_MONTH_E);
        Date dateF = new Date(ODD_MONTH_F);

        Assert.assertFalse("Test case 7 failed", date.isValid());
        Assert.assertFalse("Test case 7 failed", dateA.isValid());
        Assert.assertFalse("Test case 7 failed", dateB.isValid());
        Assert.assertFalse("Test case 7 failed", dateC.isValid());
        Assert.assertFalse("Test case 7 failed", dateD.isValid());
        Assert.assertFalse("Test case 7 failed", dateE.isValid());
        Assert.assertFalse("Test case 7 failed", dateF.isValid());

        System.out.println("Test case 7 passed.");
    }

    //Test 8
    @Test
    public void isEvenMonth() {
        Date date = new Date(EVEN_MONTH);
        Date dateA = new Date(EVEN_MONTH_A);
        Date dateB = new Date(EVEN_MONTH_B);
        Date dateC = new Date(EVEN_MONTH_C);

        Assert.assertFalse("Test case 8 failed", date.isValid());
        Assert.assertFalse("Test case 8 failed", dateA.isValid());
        Assert.assertFalse("Test case 8 failed", dateB.isValid());
        Assert.assertFalse("Test case 8 failed", dateC.isValid());

        System.out.println("Test case 8 passed.");
    }
}
