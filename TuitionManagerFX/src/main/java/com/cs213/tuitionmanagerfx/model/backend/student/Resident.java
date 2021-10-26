package com.cs213.tuitionmanagerfx.model.backend.student;

import com.cs213.tuitionmanagerfx.model.backend.Date;
import com.cs213.tuitionmanagerfx.model.config.Constants;
import com.cs213.tuitionmanagerfx.model.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.model.enums.Major;

/**
 * A model class representing a Resident student
 *
 * @author Noor, Umar
 */
public class Resident extends Student {

    private double financialAid;

    /**
     * Resident Student constructor
     *
     * @param name
     * @param major
     * @param numCredits number of credits the student is taking
     */
    public Resident(String name, Major major, int numCredits) {
        super(name, major, numCredits);
        setTuition(TuitionConfig.RES_TUITION);
        setTuitionPerCreditHour(TuitionConfig.RES_TUITION_PER_CREDIT);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
    }

    /**
     * Financial aid accessor
     *
     * @return this student's financial aid amount
     */
    public double getFinancialAid() {
        return financialAid;
    }

    /**
     * Student financial aid mutator
     *
     * @param financialAid studyAbroad value
     */
    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }

    /**
     * Calculates the tuition due for this student and stores the value in tuitionDueAmount
     */
    @Override
    public void tuitionDue() {
        double totalTuition = 0;
        if (isPartTime()) {
            totalTuition += getNumCredits() * getTuitionPerCreditHour();
            totalTuition += TuitionConfig.UNI_FEE * TuitionConfig.PART_TIME_UNI_FEE_MULTIPLIER;
        } else {
            totalTuition += getTuition() + TuitionConfig.UNI_FEE;
            if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                totalTuition += (getNumCredits() - TuitionConfig.MAX_FULL_TIME_CREDITS) * getTuitionPerCreditHour();
            }
        }
        setTuitionDueAmount(totalTuition - getTuitionPayment() - financialAid);
    }

    /**
     * Converts this Resident object to string using the superclass toString method
     *
     * @return string representation of this Resident object
     */
    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "resident";
    }
}
