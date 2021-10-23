package com.cs213.tuitionmanagerfx.implementation.test;

import com.cs213.tuitionmanagerfx.implementation.backend.Date;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * JUnit test class for Date.isValid method
 *
 * @author Noor, Umar
 */
public class DateTest {

    @Test
    public void smallInvalidYearsTest() {
        Assert.assertFalse("smallInvalidYearTest failed", new Date("11/21/800").isValid());
        Assert.assertFalse("smallInvalidYearTest failed", new Date("01/01/2020").isValid());
        Assert.assertFalse("small date test failed", new Date("01/01/-2020").isValid());

        System.out.println("smallInvalidYearTest passed.");
    }

    @Test
    public void largeInvalidYearsTest() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        Assert.assertFalse("largeInvalidYearTest failed", new Date("11/21/" + (year + 1)).isValid());
        System.out.println("largeInvalidYearTest passed.");
    }

    @Test
    public void validYearTest() {
        Assert.assertTrue("validYearsTest failed", new Date("01/01/2021").isValid());
        Assert.assertTrue("validYearsTest failed", new Date("05/20/2021").isValid());

        System.out.println("validYearsTest passed.");
    }

    @Test
    public void invalidMonthsTest() {
        Assert.assertFalse("invalidMonthsTest failed", new Date("13/21/1981").isValid());
        Assert.assertFalse("invalidMonthsTest failed", new Date("-1/21/1981").isValid());

        System.out.println("invalidMonthsTest passed.");
    }

    @Test
    public void leapYearFebTest() {
        Assert.assertFalse("leapYearFebTest failed", new Date("2/30/2020").isValid());
        Assert.assertFalse("leapYearFebTest failed", new Date("2/29/2018").isValid());

        System.out.println("leapYearFebTest passed.");
    }

    @Test
    public void smallInvalidDayTest() {
        Assert.assertFalse("smallInvalidDayTest failed", new Date("11/0/2021").isValid());
        Assert.assertFalse("smallInvalidDayTest failed", new Date("11/-5/2021").isValid());
        Assert.assertFalse("smallInvalidDayTest failed", new Date("11/32/2021").isValid());

        System.out.println("smallInvalidDayTest passed.");
    }

    @Test
    public void invalidOddMonthsDayTest() {

        Assert.assertFalse("InvalidOddMonthsDayTest failed", new Date("1/32/2021").isValid());
        Assert.assertFalse("InvalidOddMonthsDayTest failed", new Date("3/32/2021").isValid());
        Assert.assertFalse("InvalidOddMonthsDayTest failed", new Date("5/32/2021").isValid());
        Assert.assertFalse("InvalidOddMonthsDayTest failed", new Date("7/32/2021").isValid());
        Assert.assertFalse("InvalidOddMonthsDayTest failed", new Date("8/32/2021").isValid());

        System.out.println("InvalidOddMonthsDayTest passed.");
    }

    @Test
    public void validOddMonthsDayTest() {
        Assert.assertTrue("validOddMonthsDayTest failed", new Date("1/31/2021").isValid());
        Assert.assertTrue("validOddMonthsDayTest failed", new Date("3/31/2021").isValid());
        Assert.assertTrue("validOddMonthsDayTest failed", new Date("5/31/2021").isValid());
        Assert.assertTrue("validOddMonthsDayTest failed", new Date("7/31/2021").isValid());
        Assert.assertTrue("validOddMonthsDayTest failed", new Date("8/31/2021").isValid());

        System.out.println("validOddMonthsDayTest passed.");
    }

    @Test
    public void inValidEvenMonthsDayTest() {
        Assert.assertFalse("inValidEvenMonthsDayTest failed", new Date("4/31/2021").isValid());
        Assert.assertFalse("inValidEvenMonthsDayTest failed", new Date("6/31/2021").isValid());
        Assert.assertFalse("inValidEvenMonthsDayTest failed", new Date("9/31/2021").isValid());

        System.out.println("inValidEvenMonthsDayTest passed.");
    }

    @Test
    public void validEvenMonthsDayTest() {
        Assert.assertTrue("validEvenMonthsDayTest failed", new Date("4/30/2021").isValid());
        Assert.assertTrue("validEvenMonthsDayTest failed", new Date("6/30/2021").isValid());

        System.out.println("validEvenMonthsDayTest passed.");
    }
}