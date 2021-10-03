package studentmanager.student;

import studentmanager.Date;
import studentmanager.Profile;

public abstract class Student {

    private Profile profile;

    private final int minCredits = 3;
    private final int maxCredits = 24;

    private int tuition;
    private int tuitionPerCreditHour;
    private int tuitionCredit;
    private int universityFee;
    private int isPartTime;
    private int numCredits;
    private Date lastPaymentDate;

    public int getTuitionCredit() {
        return tuitionCredit;
    }

    public void setTuitionCredit(int tuitionCredit) {
        this.tuitionCredit = tuitionCredit;
    }


    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getTuitionPerCreditHour() {
        return tuitionPerCreditHour;
    }

    public void setTuitionPerCreditHour(int tuitionPerCreditHour) {
        this.tuitionPerCreditHour = tuitionPerCreditHour;
    }

    public int getUniversityFee() {
        return universityFee;
    }

    public void setUniversityFee(int universityFee) {
        this.universityFee = universityFee;
    }

    public int getIsPartTime() {
        return isPartTime;
    }

    public void setIsPartTime(int isPartTime) {
        this.isPartTime = isPartTime;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public void tuitionDue() {

    }

    private int getTuitionDueAmount() {
        return 100;
    }

    public void payTuition(int amount, Date date) {
        if (amount > 0 && amount <= getTuitionDueAmount() && date.isValid()) {
            tuitionCredit += amount;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
