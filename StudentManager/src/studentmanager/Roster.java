package studentmanager;

import studentmanager.config.Constants;
import studentmanager.student.International;
import studentmanager.student.Resident;
import studentmanager.student.Student;
import studentmanager.util.ArrayUtil;

/**
 * A class that defines a mutable, dynamically resizable list of Students that can be interacted with.
 *
 * @author Noor, Umar
 */
public class Roster {

    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    /**
     * Initializes a new roster.
     */
    public Roster() {
        roster = new Student[Constants.ROSTER_INITIAL_SIZE];
        size = 0;
    }

    /**
     * Iterates through this roster to find the index of the student, or returns NOT_FOUND.
     *
     * @param student the student to search for
     * @return index of the student, or NOT_FOUND if it is not found
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Increase the capacity of the inner array by 4.
     */
    private void grow() {
        Student[] newRoster = new Student[roster.length + Constants.ROSTER_GROWTH_AMOUNT];

        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    /**
     * Attempts to add the student to the list.
     *
     * @param student the student to add
     * @return True if added, false otherwise.
     */
    public boolean add(Student student) {
        if (find(student) >= 0) {
            return false;
        }

        if (size == roster.length) {
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    /**
     * Removes the student from the list.
     *
     * @param student the student to remove.
     * @return True if removed, false otherwise.
     */
    public boolean remove(Student student) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }

        for (int i = studentIndex; i < roster.length - 1; i++) {
            roster[i] = roster[i + 1];
        }
        roster[roster.length - 1] = null;

        size--;
        return true;
    }

    /**
     * Finds the student in the roster array, then pays the inputted tuition amount and updates lastPaymentDate
     * @param student the student to update
     * @param amount the amount of tuition to pay
     * @param date the date of the payment
     * @return true if success, false otherwise.
     */
    public boolean payTuition(Student student, double amount, Date date) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }

        if (amount > roster[studentIndex].getTuitionDueAmount()) {
            throw new IllegalArgumentException("Amount is greater than amount due.");
        }
        roster[studentIndex].payTuition(amount, date);
        return true;
    }

    /**
     * Finds the student in the roster array, then sets the study abroad status to the inputted value
     * @param student the student to find
     * @param status the new status
     * @return true if success, false otherwise.
     */
    public boolean setStudyAbroad(Student student, boolean status) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }
        try {
            International internationalStudent = (International) roster[studentIndex];
            internationalStudent.setStudyAbroad(status);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds the student in the roster array, then sets the financial aid to inputted amount
     * @param student the student to find
     * @param amount the amount of financial aid
     * @return true if success, false otherwise.
     */
    public boolean setFinancialAid(Student student, double amount) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }
        Resident resident;
        try {
            resident = (Resident) roster[studentIndex];

        } catch (Exception e) {
            throw new IllegalStateException("Not a resident student.");
        }
        if (resident.isPartTime()) {
            throw new IllegalStateException("Parttime student doesn't qualify for the award.");
        }
        if (resident.getFinancialAid() > 0) {
            throw new IllegalStateException("Awarded once already.");
        }
        resident.setFinancialAid(amount);
        return true;
    }

    /**
     * Iterates through the entire roster and calculates tuition due for each student.
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            roster[i].tuitionDue();
        }
    }

    /**
     * Displays all students to the output stream without specifying the order.
     */
    public void print() {
        if (size == 0) {
            System.out.println(Constants.EMPTY_ROSTER_MESSAGE);
        } else {
            System.out.println("* list of students in the roster **");
            ArrayUtil.print(roster, 0, size);
            System.out.println(Constants.END_ROSTER_MESSAGE);
        }
    }

    /**
     * Displays all students to the output stream, sorted by name in ascending order
     */
    public void printByStudentName() {
        if (size == 0) {
            System.out.println(Constants.EMPTY_ROSTER_MESSAGE);
        } else {
            System.out.println("* list of students ordered by name **");
            Object[] trimmedRoster = ArrayUtil.copy(roster, 0, size);
            ArrayUtil.insertionSort(trimmedRoster, (o1, o2) -> {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getProfile().getName().compareTo(s2.getProfile().getName());
            });
            ArrayUtil.print(trimmedRoster, 0, size);
            System.out.println(Constants.END_ROSTER_MESSAGE);
        }
    }

    /**
     * Displays students who have made a tuition payment to the output stream, sorted by last payment date in descending order
     */
    public void printPaymentStudentsByPaymentDate() {
        if (size == 0) {
            System.out.println(Constants.EMPTY_ROSTER_MESSAGE);
        } else {
            System.out.println("* list of students made payments ordered by payment date **");
            Object[] trimmedRoster = ArrayUtil.copy(roster, 0, size);

            for (int i = 0; i < trimmedRoster.length; i++) {
                Student s = (Student) trimmedRoster[i];
                if (s.getLastPaymentDate().compareTo(new Date(Constants.DEFAULT_DATE)) == 0) {
                    trimmedRoster[i] = null;
                }
            }
            Object[] payingStudents = ArrayUtil.filterNullValues(trimmedRoster);

            ArrayUtil.insertionSort(payingStudents, (o1, o2) -> {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getLastPaymentDate().compareTo(s2.getLastPaymentDate());
            });
            ArrayUtil.print(payingStudents, 0, payingStudents.length);
            System.out.println(Constants.END_ROSTER_MESSAGE);
        }
    }
}
