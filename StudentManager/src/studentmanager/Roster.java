package studentmanager;

import studentmanager.config.Constants;
import studentmanager.student.International;
import studentmanager.student.Student;
import studentmanager.util.ArrayUtil;

public class Roster {

    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    public Roster() {
        roster = new Student[Constants.ROSTER_INITIAL_SIZE];
        size = 0;
    }

    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    private void grow() {
        Student[] newRoster = new Student[roster.length + Constants.ROSTER_GROWTH_AMOUNT];

        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

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

    public boolean payTuition(Student student, double amount, Date date) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }

        if (roster[studentIndex].isPartTime()) {
            throw new IllegalStateException("Parttime student doesn't qualify for the award.");
        }
        if (amount > roster[studentIndex].getTuitionDueAmount()) {
            throw new IllegalArgumentException("Amount is greater than amount due.");
        }
        roster[studentIndex].payTuition(amount, date);
        return true;
    }

    public boolean setStudyAbroad(Student student, boolean status) {
        int studentIndex = find(student);
        if (studentIndex < 0) {
            return false;
        }
        International internationalStudent;
        try {
            internationalStudent = (International) roster[studentIndex];
            internationalStudent.setStudyAbroad(status);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            roster[i].tuitionDue();
        }
    }

    public void print() {
        if (size == 0) {
            System.out.println(Constants.EMPTY_ROSTER_MESSAGE);
        } else {
            System.out.println("* list of students in the roster **");
            ArrayUtil.print(roster, 0, size);
            System.out.println(Constants.END_ROSTER_MESSAGE);
        }
    }

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

    public void printPaymentStudentsByPaymentDate() {
        if (size == 0) {
            System.out.println(Constants.EMPTY_ROSTER_MESSAGE);
        } else {
            System.out.println("* list of students made payments ordered by payment date **");
            Object[] trimmedRoster = ArrayUtil.copy(roster, 0, size);

            for (Object o : trimmedRoster) {
                Student s = (Student) o;
                if (s.getLastPaymentDate() == null) {
                    o = null;
                }
            }
            Object[] payingStudents = ArrayUtil.filterNullValues(trimmedRoster);

            ArrayUtil.insertionSort(payingStudents, (o1, o2) -> {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getProfile().getName().compareTo(s2.getProfile().getName());
            });
            ArrayUtil.print(payingStudents, 0, size);
            System.out.println(Constants.END_ROSTER_MESSAGE);
        }
    }
}
