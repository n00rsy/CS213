package studentmanager;

import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;
import studentmanager.student.International;
import studentmanager.student.Resident;
import studentmanager.student.Student;

import java.util.Scanner;

public class TuitionManager {

    public void run() {

        Scanner scanner = new Scanner(System.in);
        Roster roster = new Roster();
        boolean running = true;

        System.out.println("Tuition Manager starts running.");

        while (running) {
            String input = scanner.nextLine();
            String[] tokens = input.split(Constants.INPUT_SEPARATOR);
            String command = tokens[0];

            switch (command) {
                case "AR":
                    Student student = parseStudent(tokens);
                    if (student != null) {
                        roster.add(student);
                    }
                    break;

                case "":
                    System.out.println();
                    break;

                case "Q":
                    running = false;
                    System.out.println("Tuition Manager terminated.");
                    break;
                default:
                    System.out.println("Command '" + command + "' not supported!");
                    break;
            }
        }
    }

    private Student parseStudent(String[] args) {

        // TODO: fix parsing to incorporate InputMismatchException, NoSuchElementException,

        if (args.length == 4) {

            try {
                String name = args[1];
                Major major = parseMajor(args[2]);
                int numCredits = parseNumCredits(args[3]);
                System.out.println(new Resident(name, major, numCredits));
                return new Resident(name, major, numCredits);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                return null;
            }
        }
        else if (args.length == 3) {
           System.out.println("Credit hours missing.");
        }
        else {
            System.out.println("Missing data in command line.");
        }
        return null;
    }

    private Major parseMajor(String arg) {
        switch (arg) {
            case "CS":
                return Major.CS;
            case "IT":
                return Major.IT;
            case "BA":
                return Major.BA;
            case "EE":
                return Major.EE;
            case "ME":
                return Major.ME;
            default:
                throw new IllegalArgumentException("'" + arg + "' is not a valid major.");
        }
    }

    private int parseNumCredits(String arg) {
        try {
            int numCredits = Integer.parseInt(arg);

            if (numCredits > TuitionConfig.MAX_CREDITS) {
                throw new IllegalArgumentException("Credit hours exceed the maximum 24.");
            }
            if (numCredits < 0) {
                throw new IllegalArgumentException("Credit hours cannot be negative.");
            }
            if (numCredits < TuitionConfig.MIN_CREDITS) {
                throw new IllegalArgumentException("Minimum credit hours is 3.");
            }
            return numCredits;
        }
        catch (Exception e) {
            throw new NumberFormatException("Invalid credit hours.");
        }

    }
}
