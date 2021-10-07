package studentmanager.util;

import studentmanager.Location;
import studentmanager.Major;
import studentmanager.config.TuitionConfig;
import studentmanager.student.NonResident;
import studentmanager.student.Resident;
import studentmanager.student.Student;
import studentmanager.student.TriState;

import java.util.InputMismatchException;

public class ParseUtil {

    public static Student parseStudent(String[] args) {

        if (args.length >= 3) {
            String command = args[0];
            String name = args[1];
            Major major = parseMajor(args[2]);

            if (command.length() > 0) {
                switch (command.charAt(0)) {
                    case 'S':
                    case 'F':
                    case 'T':
                    case 'R':
                        return new Student(name, major);
                    case 'A':
                        if (args.length == 3) {
                            throw new InputMismatchException("Credit hours missing.");
                        }
                        int numCredits = parseNumCredits(args[3]);
                        if (command.length() == 2) {
                            switch (command.charAt(1)) {
                                case 'R':
                                    return new Resident(name, major, numCredits);
                                case 'N':
                                    return new NonResident(name, major, numCredits);
                                case 'T':
                                    if (args.length != 5) {
                                        throw new IllegalArgumentException("Missing data in command line.");
                                    }
                                    Location location = parseLocation(args[4]);
                                    return new TriState(name, major, numCredits, location);
                                case 'I':
                                    // TODO: parse international
                                    break;
                            }
                        }
                        throw new IllegalArgumentException("Command '" + command + "' not supported!");
                }
            }
            throw new IllegalArgumentException("Command '" + command + "' not supported!");
        }
        throw new IllegalArgumentException("Missing data in command line.");
    }

    public static Major parseMajor(String arg) {
        switch (arg.toUpperCase()) {
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

    public static int parseNumCredits(String arg) {
        int numCredits;
        try {
            numCredits = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new NumberFormatException("Invalid credit hours.");
        }
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

    public static Location parseLocation(String arg) {
        switch (arg.toUpperCase()) {
            case "NY":
                return Location.NY;
            case "CT":
                return Location.CT;
            default:
                throw new IllegalArgumentException("Not part of the tri-state area.");
        }
    }

}
