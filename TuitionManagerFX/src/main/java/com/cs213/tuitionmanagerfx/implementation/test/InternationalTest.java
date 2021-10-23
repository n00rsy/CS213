package com.cs213.tuitionmanagerfx.implementation.test;

import com.cs213.tuitionmanagerfx.implementation.backend.student.International;
import com.cs213.tuitionmanagerfx.implementation.enums.Major;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test class for International.tuitionDue method
 *
 * @author Noor, Umar
 */

public class InternationalTest {

    private static final double DELTA = 1e-15;

    @Test
    public void tuitionDueNotCalledTest() {
        International international = new International("Noor Syed", Major.CS, 12, false);
        Assert.assertEquals(0, international.getTuitionDueAmount(), DELTA);
    }

    @Test
    public void nonStudyAbroadFullTimeTest() {
        International international = new International("Noor Syed", Major.CS, 12, false);
        international.tuitionDue();
        Assert.assertEquals(35655, international.getTuitionDueAmount(), DELTA);
    }

    @Test
    public void nonStudyAbroadPartTimeTest() {
        International international = new International("Noor Syed", Major.CS, 6, false);
        international.tuitionDue();
        Assert.assertEquals(11060.4, international.getTuitionDueAmount(), DELTA);
    }

    @Test
    public void studyAbroadFullTimeTest() {
        International international = new International("Noor Syed", Major.CS, 12, true);
        international.tuitionDue();
        Assert.assertEquals(5918, international.getTuitionDueAmount(), DELTA);
    }
}
