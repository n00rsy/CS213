package com.cs213.tuitionmanagerfx.implementation.backend.student;

import com.cs213.tuitionmanagerfx.implementation.backend.Date;
import com.cs213.tuitionmanagerfx.implementation.config.Constants;
import com.cs213.tuitionmanagerfx.implementation.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.implementation.enums.Major;

/**
 * A model class representing a NonResident student.
 * International, TriState both derive from this class.
 *
 * @author Noor, Umar
 */
public class NonResident extends Student {

    /**
     * NonResident Student constructor
     *
     * @param name
     * @param major
     * @param numCredits number of credits the student is taking
     */
    public NonResident(String name, Major major, int numCredits) {
        super(name, major, numCredits);
        setTuition(TuitionConfig.NONRES_TUITION);
        setTuitionPerCreditHour(TuitionConfig.NONRES_TUITION_PER_CREDIT);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
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
        setTuitionDueAmount(totalTuition - getTuitionPayment());
    }

    /**
     * Converts this NonResident object to string using the superclass toString method
     *
     * @return string representation of this NonResident object
     */
    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "non-resident";
    }

}
