package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class Resident extends Student {

    private final int maxFinancialAid = 10000;
    private int financialAid;

    public Resident(String name, Major major, int numCredits) {
        super(name, major, numCredits);
        setTuition(TuitionConfig.RES_TUITION);
        setTuitionPerCreditHour(TuitionConfig.RES_TUITION_PER_CREDIT);
        setUniversityFee(TuitionConfig.UNI_FEE);
        setTuitionCredit(TuitionConfig.RES_DEFAULT_FIN_AID_AMOUNT);
        setLastPaymentDate(new Date(Constants.DEFAULT_DATE));
    }

    public int getFinancialAid() {
        return financialAid;
    }

    public void setFinancialAid(int financialAid) {
        this.financialAid = financialAid;
    }

    @Override
    public void tuitionDue() {
        double totalTuition = 0;
        if (isPartTime()) {
            totalTuition += getNumCredits() * getTuitionPerCreditHour();
            totalTuition += getUniversityFee() * TuitionConfig.PART_TIME_RESIDENT_UNI_FEE_MULTIPLIER;
        }
        else {
            totalTuition += getTuition() + getUniversityFee();
            if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                totalTuition += (getNumCredits() - TuitionConfig.MAX_FULL_TIME_CREDITS) * getTuitionPerCreditHour();
            }
        }
        setTuitionDueAmount(totalTuition - getTuitionCredit());
    }

    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "resident";
    }
}
