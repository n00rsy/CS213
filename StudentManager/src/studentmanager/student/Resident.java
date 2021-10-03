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
        setTuition(TuitionConfig.RESIDENT_TUITION);
        setTuitionPerCreditHour(TuitionConfig.RESIDENT_TUITION_PER_CREDIT);
        setFinancialAid(TuitionConfig.DEFAULT_FIN_AID_AMOUNT);
        setTuitionCredit(0);
        setNumCredits(numCredits);
        setProfile(new Profile(name, major));
    }

    public void tutionDue() {

    }

    private int getTuitionDueAmount() {
        return 200;
    }
}
