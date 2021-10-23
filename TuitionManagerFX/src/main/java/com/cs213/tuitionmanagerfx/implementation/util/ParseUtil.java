package com.cs213.tuitionmanagerfx.implementation.util;

import com.cs213.tuitionmanagerfx.implementation.backend.student.*;
import com.cs213.tuitionmanagerfx.implementation.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.implementation.enums.Location;
import com.cs213.tuitionmanagerfx.implementation.enums.Major;

import java.util.InputMismatchException;

/**
 * A utility class to parse arguments from the command line.
 *
 * @author Noor, Umar
 */
public class ParseUtil {

    /**
     * Parses a student from an inputted array of strings
     *
     * @param args array of strings to parse
     * @return new student object
     */
    public static Student parseStudent(String[] args) {

        if (args.length >= 3) {
            String command = args[0];
            String name = parseName(args[1]);
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
                                    if (args.length != 5) {
                                        throw new IllegalArgumentException("Missing data in command line.");
                                    }
                                    boolean studyAbroad = parseBoolean(args[4]);

                                    if (numCredits < 12) {
                                        throw new IllegalArgumentException("International students must enroll at least 12 credits.");
                                    }

                                    return new International(name, major, numCredits, studyAbroad);
                            }
                        }
                        throw new IllegalArgumentException("Command '" + command + "' not supported!");
                }
            }
            throw new IllegalArgumentException("Command '" + command + "' not supported!");
        }
        throw new IllegalArgumentException("Missing data in command line.");
    }

    public static String parseName(String arg) {
        arg = arg.trim();
        if (arg.length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        return arg;
    }

    /**
     * Parses a Major from a string
     *
     * @param arg input string
     * @return Major corresponding to input
     */
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

    /**
     * Parses the number of credits from a string and checks to ensure its value is valid
     *
     * @param arg string to parse
     * @return int numCredits
     */
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

    /**
     * Parses a Location object from a string
     *
     * @param arg string to parse
     * @return new Location object
     */
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

    /**
     * Parses a boolean from a string containing 'true' or 'false'
     *
     * @param arg string to parse
     * @return parsed boolean
     */
    public static boolean parseBoolean(String arg) {
        switch (arg.toUpperCase()) {
            case "TRUE":
                return true;
            case "FALSE":
                return false;
            default:
                throw new IllegalArgumentException("Invalid boolean.");
        }
    }
}
