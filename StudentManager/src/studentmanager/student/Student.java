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
    private int tuitionPayment;
    private int tuitionDueAmount;
    private int universityFee;
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

    public int getTuitionPayment() {
        return tuitionPayment;
    }

    public void setTuitionPayment(int tuitionPayment) {
        this.tuitionPayment = tuitionPayment;
    }

    public int getTuitionDueAmount() {
        return tuitionDueAmount;
    }

    public void setTuitionDueAmount(int tuitionDueAmount) {
        this.tuitionDueAmount = tuitionDueAmount;
    }

    public void tuitionDue() {

    }

    public boolean isPartTime() {
        return numCredits < TuitionConfig.MIN_FULL_TIME_CREDITS;
    }


    public void payTuition(int amount, Date date) {

        if (amount > 0 && amount <= getTuitionDueAmount() && date.isValid()) {
            tuitionCredit += amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return  false;
        }
        Student other = (Student) o;
        return profile.equals(other.getProfile());
    }

    @Override
    public String toString() {
        return getProfile().getName() + Constants.OUTPUT_SEPARATOR +
                getProfile().getMajor() + Constants.OUTPUT_SEPARATOR +
                getNumCredits() + " credit hours" + Constants.OUTPUT_SEPARATOR +
                "tuition due:" + getTuitionDueAmount() + Constants.OUTPUT_SEPARATOR +
                "total payment:" + getTuitionPayment() + Constants.OUTPUT_SEPARATOR +
                "payment date: " + getLastPaymentDate().toString()
                ;
    }
}
