package songlibrary.test;

import org.junit.Assert;
import org.junit.Test;
import songlibrary.Date;

public class DateTest {

    private static String SMALL_YEAR = "11/21/800";

    // Test 1
    @Test
    public void isValidMinDateTest() {
        Date date = new Date(SMALL_YEAR);
        Assert.assertFalse("Test case 1 failed",date.isValid());
        System.out.println("Test case 1 passed.");
    }
}
