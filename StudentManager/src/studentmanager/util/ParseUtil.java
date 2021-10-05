package studentmanager.util;

import studentmanager.Major;
import studentmanager.config.TuitionConfig;
import studentmanager.student.Resident;
import studentmanager.student.Student;

import java.util.InputMismatchException;

public class ParseUtil {


    public static Student parseStudent(String[] args) {

        if (args.length == 4) {

            String type = args[0];
            String name = args[1];
            Major major = parseMajor(args[2]);
            int numCredits = parseNumCredits(args[3]);

            switch (type) {
                case "AR":
                    return new Resident(name, major, numCredits);
                default:
                    throw new IllegalArgumentException("Command '" + type + "' not supported!");
            }
        } else if (args.length == 3) {
            throw new InputMismatchException("Credit hours missing.");
        } else {
            throw new InputMismatchException("Missing data in command line.");
        }
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

}
