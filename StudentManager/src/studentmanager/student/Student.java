package studentmanager.student;

import studentmanager.Date;
import studentmanager.Profile;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public abstract class Student {

    private Profile profile;

    private int tuition;
    private int tuitionPerCreditHour;
    private int tuitionCredit;
    private int universityFee;
    private int numCredits;
    private Date lastPaymentDate;
    private int lastPaymentAmount;

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

    public int getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(int lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public void tuitionDue() {

    }

    public boolean isPartTime() {
        return numCredits < TuitionConfig.MIN_FULL_TIME_CREDITS;
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
        return getProfile().getName() + Constants.OUTPUT_SEPARATOR +
                getProfile().getMajor() + Constants.OUTPUT_SEPARATOR +
                getNumCredits() + " credit hours" + Constants.OUTPUT_SEPARATOR +
                "tuition due:" + getTuitionDueAmount() + Constants.OUTPUT_SEPARATOR +
                "last payment:" + getLastPaymentAmount() + Constants.OUTPUT_SEPARATOR +
                "payment date: " + getLastPaymentDate().toString()
                ;
    }
}
