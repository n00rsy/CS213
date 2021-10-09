package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class International extends NonResident {

    private final int additionalFees = 2650;
    private boolean studyAbroad;

    public International(String name, Major major, int numCredits, boolean studyAbroad) {

        super(name, major, numCredits);
        setTuition(TuitionConfig.INTERNAT_TUITION);
        setUniversityFee(TuitionConfig.UNI_FEE);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
        setStudyAbroad(studyAbroad);
        setTuitionPerCreditHour(TuitionConfig.NONRES_TUITION_PER_CREDIT);
    }

    public boolean isStudyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    @Override
    public void tuitionDue() {
        double totalTuition = 0;
        if (isPartTime()) {
            if (!isStudyAbroad())
                totalTuition += getNumCredits() * getTuitionPerCreditHour();
            totalTuition += getUniversityFee() * TuitionConfig.PART_TIME_UNI_FEE_MULTIPLIER;
        }
        else {
            totalTuition += getUniversityFee();
            if (!isStudyAbroad()) {
                totalTuition += getTuition();
                if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                    totalTuition += (getNumCredits() - TuitionConfig.MAX_FULL_TIME_CREDITS) * getTuitionPerCreditHour();
                }
            }
        }
        setTuitionDueAmount(totalTuition - getTuitionCredit() - getTuitionPayment());
    }

    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "international" + ((studyAbroad) ? (Constants.OUTPUT_SEPARATOR + "study abroad") : "");
    }
}
