package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

/**
 * A model class representing an International student
 * @author Noor, Umar
 */
public class International extends NonResident {

    private boolean studyAbroad;

    /**
     * International Student constructor
     * @param name
     * @param major
     * @param numCredits number of credits the student is taking
     * @param studyAbroad true if the student is studying abroad, false otherwise
     */
    public International(String name, Major major, int numCredits, boolean studyAbroad) {

        super(name, major, numCredits);
        setTuition(TuitionConfig.INTERNAT_TUITION);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
        setTuitionPerCreditHour(TuitionConfig.NONRES_TUITION_PER_CREDIT);
        this.studyAbroad = studyAbroad;
    }

    /**
     * Student study abroad mutator
     *
     * @param studyAbroad new studyAbroad value
     */
    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    /**
     * Calculates the tuition due for this student and stores the value in tuitionDueAmount
     */
    @Override
    public void tuitionDue() {
        double totalTuition = TuitionConfig.INTERNAT_ADD_FEE;
        if (isPartTime()) {
            if (!studyAbroad)
                totalTuition += getNumCredits() * getTuitionPerCreditHour();
            totalTuition += TuitionConfig.UNI_FEE * TuitionConfig.PART_TIME_UNI_FEE_MULTIPLIER;
        } else {
            totalTuition += TuitionConfig.UNI_FEE;
            if (!studyAbroad) {
                totalTuition += getTuition();
                if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                    totalTuition += (getNumCredits() - TuitionConfig.MAX_FULL_TIME_CREDITS) * getTuitionPerCreditHour();
                }
            }
        }
        setTuitionDueAmount(totalTuition - getTuitionPayment());
    }

    /**
     * Converts this International object to string using the superclass toString method
     * @return string representation of this International object
     */
    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "international" + ((studyAbroad) ? (Constants.OUTPUT_SEPARATOR + "study abroad") : "");
    }
}
