package studentmanager.student;

import studentmanager.Date;
import studentmanager.Major;
import studentmanager.Profile;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;
/**
 * A model class representing a student.
 * All other student classes derive from this class.
 * @author Noor, Umar
 */
public class Student {

    private double tuition;
    private double tuitionPerCreditHour;
    private double tuitionPayment;
    private double tuitionDueAmount;
    private int numCredits;
    private Date lastPaymentDate;
    private Profile profile;

    /**
     * Student Constructor
     * @param name
     * @param major
     */
    public Student(String name, Major major) {
        setProfile(new Profile(name, major));
    }

    /**
     * Student Constructor
     * @param name
     * @param major
     * @param numCredits
     */
    public Student(String name, Major major, int numCredits) {
        setNumCredits(numCredits);
        setProfile(new Profile(name, major));
    }

    /**
     * Student profile accessor
     * @return this student's profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Student profile mutator
     * @param profile new profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Student profile accessor
     * @return this student's profile
     */
    public double getTuition() {
        return tuition;
    }

    /**
     * Student tuition mutator
     * @param tuition new tuition
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    /**
     * Student profile accessor
     * @return this student's profile
     */
    public double getTuitionPerCreditHour() {
        return tuitionPerCreditHour;
    }

    /**
     * Student tuition per credit hour mutator
     * @param tuitionPerCreditHour new tuition per credit hour
     */
    public void setTuitionPerCreditHour(double tuitionPerCreditHour) {
        this.tuitionPerCreditHour = tuitionPerCreditHour;
    }

    /**
     * Total tuition payment accessor
     * @return this student's total tuition payment
     */
    public double getTuitionPayment() {
        return tuitionPayment;
    }

    /**
     * Tuition due amount accessor
     * @return this student's tuition due
     */
    public double getTuitionDueAmount() {
        return tuitionDueAmount;
    }

    /**
     * Student tuition due amount mutator
     * @param tuitionDueAmount new tuition due amount
     */
    public void setTuitionDueAmount(double tuitionDueAmount) {
        this.tuitionDueAmount = tuitionDueAmount;
    }

    /**
     * Accessor for number of credit hours
     * @return this student's credit hours
     */
    public int getNumCredits() {
        return numCredits;
    }

    /**
     * number of credit hours mutator
     * @param numCredits new number of credit hours
     */
    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    /**
     * Student last payment date accessor
     * @return this student's last payment date
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Last payment date mutator
     * @param lastPaymentDate new last payment date
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Do-nothing method overridden in subclasses to calculate tuition due
     */
    public void tuitionDue() {

    }

    /**
     * Checks if the student is part time
     * @return true if part time, false otherwise
     */
    public boolean isPartTime() {
        return numCredits < TuitionConfig.MIN_FULL_TIME_CREDITS;
    }

    /**
     * Increases tuitionPayment by the amount passed in and sets new lastPaymentDate
     * @param amount payment to process
     * @param date date of payment
     */
    public void payTuition(double amount, Date date) {
        tuitionPayment += amount;
        setLastPaymentDate(date);
    }

    /**
     * Checks if this student is equal to another object by checking the equality of their profiles.
     * @param o the object to check against
     * @return true if the student profiles are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student other = (Student) o;
        return profile.equals(other.getProfile());
    }

    /**
     * Converts this NonResident object to string using the superclass toString method
     * @return string representation of this Student object
     */
    @Override
    public String toString() {
        return getProfile().getName() + Constants.OUTPUT_SEPARATOR +
                getProfile().getMajor() + Constants.OUTPUT_SEPARATOR +
                getNumCredits() + " credit hours" + Constants.OUTPUT_SEPARATOR +
                "tuition due:" + String.format(Constants.PRICE_FORMAT, getTuitionDueAmount()) + Constants.OUTPUT_SEPARATOR +
                "total payment:" + String.format(Constants.PRICE_FORMAT, getTuitionPayment()) + Constants.OUTPUT_SEPARATOR +
                "last payment date: " + getLastPaymentDate().toString()
                ;
    }
}
