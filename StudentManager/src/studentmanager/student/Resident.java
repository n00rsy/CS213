package studentmanager.student;

import studentmanager.Major;
import studentmanager.Profile;
import studentmanager.config.TuitionConfig;

public class Resident extends Student {

    private final int maxFinancialAid = 10000;

    public int getFinancialAid() {
        return financialAid;
    }

    public void setFinancialAid(int financialAid) {
        this.financialAid = financialAid;
    }

    private int financialAid;

    public Resident(String name, Major major, int numCredits) {
        setTuition(TuitionConfig.RES_TUITION);
        setTuitionPerCreditHour(TuitionConfig.RES_TUITION_PER_CREDIT);
        setUniversityFee(TuitionConfig.RES_UNI_FEE);
        setTuitionCredit(TuitionConfig.RES_DEFAULT_FIN_AID_AMOUNT);
        setNumCredits(numCredits);
        setProfile(new Profile(name, major));
    }

    public void tutionDue() {
        int totalTuition = getTuitionDueAmount();
    }

    private int getTuitionDueAmount() {
        int totalTuition = getTuition() + getUniversityFee();
        if (isPartTime()) {
            totalTuition += getNumCredits() * getTuitionPerCreditHour();
        }
        else {
            totalTuition += getTuition();
            if (getNumCredits() > TuitionConfig.MAX_FULL_TIME_CREDITS) {
                totalTuition += (getNumCredits() - TuitionConfig.MAX_FULL_TIME_CREDITS) * getTuitionPerCreditHour();
            }
        }
        return totalTuition - getTuitionCredit();
    }
}
