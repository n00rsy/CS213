package studentmanager.student;

import studentmanager.Date;
import studentmanager.Profile;

public abstract class Student {

    private Profile profile;

    private final int minCredits = 3;
    private final int maxCredits = 24;

    private int tuition;
    private int tuitionPerCreditHour;
    private int universityFee;
    private int isPartTime;

    private int numCredits;

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    private Date lastPaymentDate;

    public void tuitionDue() {

    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "";
    }
}
