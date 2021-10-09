package studentmanager;

import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;
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

                case "R":
                    removeStudent(args, roster);
                    break;

                case "C":
                    calculateTuition(roster);
                    break;

                case "T":
                    payTuition(args, roster);
                    break;

                case "S":
                    setStudyAbroad(args, roster);
                    break;

                case "F":
                    setFinancialAid(args, roster);
                    break;

                case "P":
                    roster.print();
                    break;

                case "PN":
                    roster.printByStudentName();
                    break;

                case "PT":
                    roster.printPaymentStudentsByPaymentDate();
                    break;

                case "":
                    System.out.println();
                    break;

                case "Q":
                    running = false;
                    System.out.println("\nTuition Manager terminated.");
                    break;

                default:
                    System.out.println("Command '" + args[0] + "' not supported!");
                    break;
            }
        }
    }

    private void addStudent(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        if (student != null) {
            if (roster.add(student)) {
                System.out.println("Student added.");
            } else {
                System.out.println("Student is already in the roster.");
            }
        }
    }

    private void removeStudent(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        if (student != null) {
            if (roster.remove(student)) {
                System.out.println("Student removed from the roster.");
            } else {
                System.out.println("Student is not in the roster.");
            }
        }
    }

    private void calculateTuition(Roster roster) {
        roster.calculateTuition();
        System.out.println("Calculation completed.");
    }

    private void payTuition(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        if (args.length == 3) {
            System.out.println("Payment amount missing.");

        } else if (args.length == 4) {
            System.out.println("Missing data in command line.");
        } else {
            double amount = 0;
            Date date = new Date(args[4]);
            try {
                amount = Double.parseDouble(args[3]);
            } catch (Exception e) {
                System.out.println(Constants.INVALID_AMOUNT_MESSAGE);
                return;
            }
            if (amount <= 0) {
                System.out.println(Constants.INVALID_AMOUNT_MESSAGE);
            } else if (date == null || !date.isValid() || date.compareTo(new Date()) > 0 && date.compareTo(new Date("01/01/2021")) < 0) {
                System.out.println("Payment date invalid.");
            } else {
                try {
                    if (roster.payTuition(student, amount, date)) {
                        System.out.println("Payment applied.");
                    } else {
                        System.out.println("Student not in the roster.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }

    private void setStudyAbroad(String[] args, Roster roster) {
        Student student = null;
        boolean status = false;
        try {
            student = ParseUtil.parseStudent(args);
            status = ParseUtil.parseBoolean(args[3]);
            if (roster.setStudyAbroad(student, status)) {
                System.out.println("Tuition updated.");
            } else {
                System.out.println("Couldn't find the international student.");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void setFinancialAid(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        if (args.length == 3) {
            System.out.println("Missing the amount.");
        } else {
            double amount = 0;
            try {
                amount = Double.parseDouble(args[3]);
            } catch (Exception e) {
                System.out.println(Constants.INVALID_AMOUNT_MESSAGE);
                return;
            }
            if (amount <= 0 || amount > TuitionConfig.MAX_FIN_AID) {
                System.out.println(Constants.INVALID_AMOUNT_MESSAGE);
            } else {
                try {
                    if (roster.setFinancialAid(student, amount)) {
                        System.out.println("Tuition updated.");
                    } else {
                        System.out.println("Student not in the roster.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }
}
