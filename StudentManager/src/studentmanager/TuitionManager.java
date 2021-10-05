package studentmanager;

import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;
import studentmanager.student.International;
import studentmanager.student.Resident;
import studentmanager.student.Student;
import studentmanager.util.ParseUtil;

import java.util.Scanner;

public class TuitionManager {

    public void run() {

        Scanner scanner = new Scanner(System.in);
        Roster roster = new Roster();
        boolean running = true;

        System.out.println("Tuition Manager starts running.");

        while (running) {
            String input = scanner.nextLine();
            String[] args = input.split(Constants.INPUT_SEPARATOR);
            String command = args[0];

            switch (command) {
                case "AT":
                case "AI":
                case "AN":
                case "AR":
                    addStudent(args, roster);
                    break;

                case "":
                    System.out.println();
                    break;

                case "Q":
                    running = false;
                    System.out.println("Tuition Manager terminated.");
                    break;
                default:
                    System.out.println("Command '" + args[0] + "' not supported!");
                    break;
            }
        }
    }

    private void addStudent(String[] args, Roster roster) {
        Student student = ParseUtil.parseStudent(args);
        if (student != null) {
            if (roster.add(student)) {
                System.out.println("Student added.");
            }
            else {
                System.out.println("Student is already in the roster.");
            }
        }
    }
}
