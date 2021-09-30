package studentmanager;

import studentmanager.student.Student;

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

        for (int i = studentIndex; i < roster.length-1; i++) {
            roster[i] = roster[i + 1];
        }
        roster[roster.length-1] = null;

        size--;
        return true;
    }
}
