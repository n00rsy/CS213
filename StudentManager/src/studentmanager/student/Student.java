package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.Profile;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class Student {

    public Student(String name, Major major) {
        setProfile(new Profile(name, major));
    }

    public Student(String name, Major major, int numCredits) {
        setNumCredits(numCredits);
        setProfile(new Profile(name, major));
    }

    private double tuition;
    private double tuitionPerCreditHour;
    private double tuitionCredit;
    private double tuitionPayment;
    private double tuitionDueAmount;
    private double universityFee;
    private int numCredits;
    private Date lastPaymentDate;
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public double getTuitionPerCreditHour() {
        return tuitionPerCreditHour;
    }

    public void setTuitionPerCreditHour(double tuitionPerCreditHour) {
        this.tuitionPerCreditHour = tuitionPerCreditHour;
    }

    public double getTuitionCredit() {
        return tuitionCredit;
    }

    public void setTuitionCredit(double tuitionCredit) {
        this.tuitionCredit = tuitionCredit;
    }

    public double getTuitionPayment() {
        return tuitionPayment;
    }

    public void setTuitionPayment(double tuitionPayment) {
        this.tuitionPayment = tuitionPayment;
    }

    public double getTuitionDueAmount() {
        return tuitionDueAmount;
    }

    public void setTuitionDueAmount(double tuitionDueAmount) {
        this.tuitionDueAmount = tuitionDueAmount;
    }

    public double getUniversityFee() {
        return universityFee;
    }

    public void setUniversityFee(double universityFee) {
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

    public void tuitionDue() {

    }

    public boolean isPartTime() {
        return numCredits < TuitionConfig.MIN_FULL_TIME_CREDITS;
    }


    public void payTuition(double amount, Date date) {

        if (amount > 0 && amount <= getTuitionDueAmount() && date.isValid()) {
            tuitionCredit += amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student other = (Student) o;
        return profile.equals(other.getProfile());
    }

    @Override
    public String toString() {
        return getProfile().getName() + Constants.OUTPUT_SEPARATOR +
                getProfile().getMajor() + Constants.OUTPUT_SEPARATOR +
                getNumCredits() + " credit hours" + Constants.OUTPUT_SEPARATOR +
                "tuition due:" + String.format("%.2f", getTuitionDueAmount()) + Constants.OUTPUT_SEPARATOR +
                "total payment:" + String.format("%.2f",getTuitionPayment()) + Constants.OUTPUT_SEPARATOR +
                "last payment date: " + getLastPaymentDate().toString()
                ;
    }
}
