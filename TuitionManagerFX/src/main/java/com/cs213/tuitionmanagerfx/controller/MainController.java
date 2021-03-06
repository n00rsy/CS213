package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.model.backend.Date;
import com.cs213.tuitionmanagerfx.model.backend.Roster;
import com.cs213.tuitionmanagerfx.model.backend.student.*;
import com.cs213.tuitionmanagerfx.model.config.Constants;
import com.cs213.tuitionmanagerfx.model.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.model.enums.Location;
import com.cs213.tuitionmanagerfx.model.enums.Major;
import com.cs213.tuitionmanagerfx.model.util.ParseUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * The main controller for the project containing the roster instance and links to navigate the rest of the program.
 *
 * @author Noor, Umar
 */
public class MainController {

    /**
     * TextArea for all program output.
     */
    @FXML
    TextArea output;

    /**
     * Student Type group for use in creating new students.
     */
    @FXML
    ToggleGroup studentType;

    /**
     * Major toggleGroup for use in creating new students.
     */
    @FXML
    ToggleGroup addMajor;

    /**
     * Name TextField for use in creating new students.
     */
    @FXML
    TextField addName;

    /**
     * Number of credits input for use in creating new students.
     */
    @FXML
    TextField numCredits;

    /**
     * Container node for studyAbroad fields in student creation.
     */
    @FXML
    HBox studyAbroadContainer;

    /**
     * studyAbroad toggleGroup for use in student creation.
     */
    @FXML
    ToggleGroup addStudyAbroad;

    /**
     * Location selector container for use in student creation.
     */
    @FXML
    HBox locationContainer;

    /**
     * Location toggleGroup for use in adding students.
     */
    @FXML
    ToggleGroup locationGroup;

    /**
     * Name TextField for use in removing students.
     */
    @FXML
    TextField removeName;

    /**
     * Major toggleGroup for use in removing existing students from the roster.
     */
    @FXML
    ToggleGroup removeMajor;

    /**
     * Name TextField for use in editing students.
     */
    @FXML
    TextField editName;

    /**
     * Major toggleGroup for use in editing existing students.
     */
    @FXML
    ToggleGroup editMajor;

    /**
     * studyAbroad toggleGroup for use in changing a student's study abroad status.
     */
    @FXML
    ToggleGroup editStudyAbroad;

    /**
     * Financial aid textField for changing a student's financial aid amount.
     */
    @FXML
    TextField editFinancialAid;

    /**
     * TextField used to input the name of the student to pay tuition.
     */
    @FXML
    TextField payName;


    /**
     * ToggleGroup to input the major of the student to pay tuition.
     */
    @FXML
    ToggleGroup payMajor;


    /**
     * TextField used to input the amount of tuition to pay.
     */
    @FXML
    TextField amount;

    /**
     * TextField used to input the tuition payment date.
     */
    @FXML
    TextField date;

    /**
     * ToggleGroup used to select which sort type to use while printing.
     */
    @FXML
    ToggleGroup printType;

    /**
     * Displays printed students.
     */
    @FXML
    ListView studentView;

    Roster roster;

    /**
     * Runs on start and initializes the roster.
     */
    public void initialize() {
        roster = new Roster();
    }

    /**
     * Handles button click event when different student types are selected, and activates/ deactivates the correct fields
     *
     * @param event
     */
    @FXML
    private void handleStudentTypeChange(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        if (radioButton.getText().equals(com.cs213.tuitionmanagerfx.util.Constants.INTERNATIONAL)) {
            studyAbroadContainer.setDisable(false);
        } else {
            studyAbroadContainer.setDisable(true);
            addStudyAbroad.selectToggle(null);
        }

        if (radioButton.getText().equals(com.cs213.tuitionmanagerfx.util.Constants.TRISTATE)) {
            locationContainer.setDisable(false);
        } else {
            locationContainer.setDisable(true);
            locationGroup.selectToggle(null);
        }
    }

    /**
     * Handles the add student button click event and attempts to add a student to the roster. If successful, returns to the main menu.
     */
    @FXML
    private void handleAddStudentButtonClick() {
        try {

            if (studentType.getSelectedToggle() == null)
                throw new IllegalArgumentException("Please select a student type.");
            if (addMajor.getSelectedToggle() == null) throw new IllegalArgumentException("Please select a major.");

            Student student;
            String inputName = ParseUtil.parseName(addName.getText());
            String inputType = ((RadioButton) studentType.getSelectedToggle()).getText();
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) addMajor.getSelectedToggle()).getText());
            int inputNumCredits = ParseUtil.parseNumCredits(numCredits.getText());
            switch (inputType) {
                case com.cs213.tuitionmanagerfx.util.Constants.INTERNATIONAL:
                    if (addStudyAbroad.getSelectedToggle() == null)
                        throw new IllegalArgumentException("Please select a study abroad status.");
                    boolean inputStudyAbroad = ParseUtil.parseBoolean(((RadioButton) addStudyAbroad.getSelectedToggle()).getText());
                    student = new International(inputName,
                            inputMajor,
                            inputNumCredits,
                            inputStudyAbroad);
                    break;

                case com.cs213.tuitionmanagerfx.util.Constants.RESIDENT:
                    student = new Resident(inputName, inputMajor, inputNumCredits);
                    break;

                case com.cs213.tuitionmanagerfx.util.Constants.NONRESIDENT:
                    student = new NonResident(inputName, inputMajor, inputNumCredits);
                    break;

                case com.cs213.tuitionmanagerfx.util.Constants.TRISTATE:
                    if (locationGroup.getSelectedToggle() == null)
                        throw new IllegalArgumentException("Please select a location.");
                    Location inputLocation = ParseUtil.parseLocation(((RadioButton) locationGroup.getSelectedToggle()).getText());
                    student = new TriState(inputName, inputMajor, inputNumCredits, inputLocation);
                    break;
                default:
                    throw new IllegalArgumentException("Please select a student type.");
            }
            addStudent(student);
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Handles the remove student click event and attempts to remove a student.
     */
    @FXML
    private void handleRemoveStudentButtonClick() {
        try {
            if (removeMajor.getSelectedToggle() == null) throw new IllegalArgumentException("Please select a major.");
            String inputName = ParseUtil.parseName(removeName.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) removeMajor.getSelectedToggle()).getText());
            Student student = new Student(inputName, inputMajor);
            removeStudent(student);
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Handles the edit student click event and attempts to edit the student in the requested way.
     */
    @FXML
    private void handleEditStudentButtonClick() {
        try {
            if (editMajor.getSelectedToggle() == null) throw new IllegalArgumentException("Please select a major.");

            String inputName = ParseUtil.parseName(editName.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) editMajor.getSelectedToggle()).getText());
            Student student = new Student(inputName, inputMajor);
            RadioButton studyAbroadToggle = (RadioButton) editStudyAbroad.getSelectedToggle();

            if (studyAbroadToggle != null) {
                setStudyAbroad(student, ParseUtil.parseBoolean(studyAbroadToggle.getText()));
            } else {
                try {
                    double financialAidAmount = Double.parseDouble(editFinancialAid.getText());
                    setFinancialAid(student, financialAidAmount);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Please submit a valid change to the student.");
                }
            }
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Handles the pay tuition click event and attempts to submit a payment.
     */
    @FXML
    private void handlePayTuitionButtonClick() {
        try {

            if (date.getText().trim().length() == 0) {
                throw new IllegalArgumentException("Please enter a payment date.");
            }
            if (amount.getText().trim().length() == 0) {
                throw new IllegalArgumentException("Please enter a payment amount.");
            }

            if (payMajor.getSelectedToggle() == null) throw new IllegalArgumentException("Please select a major.");

            String inputName = ParseUtil.parseName(payName.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) payMajor.getSelectedToggle()).getText());
            Date paymentDate = new Date(date.getText());
            double paymentAmount = Double.parseDouble(amount.getText());

            Student student = new Student(inputName, inputMajor);
            payTuition(student, paymentAmount, paymentDate);
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Handles the calculate tuition button click by switching calculating tuition for all students in the roster.
     */
    @FXML
    private void handleCalculateTuitionDueButtonClick() {
        roster.calculateTuition();
        output.appendText("\n" + "Tuition calculated successfully.");
    }

    /**
     * Handles the print roster click event and attempts to display roster based on input.
     */
    @FXML
    private void handlePrintRosterButtonClick() {
        try {
            String currentPrintType = "";
            try {
                currentPrintType = ((RadioButton) printType.getSelectedToggle()).getId();
            } catch (Exception e) {
                throw new IllegalArgumentException("Please select a print type.");
            }

            switch (currentPrintType) {
                case "unordered":
                    showRosterUnordered();
                    break;
                case "name":
                    showRosterByStudentName();
                    break;
                case "payment":
                    showRosterByPaymentDate();
                    break;
                default:
                    throw new IllegalArgumentException("Something is wrong!");
            }
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Adds a student to the collection and outputs the result to the output TextArea.
     *
     * @param student the student to add
     */
    private void addStudent(Student student) {
        if (student != null) {
            if (roster.add(student)) {
                output.appendText("\n" + "Student added.");
            } else {
                output.appendText("\n" + "Student is already in the roster.");
            }
        }
    }

    /**
     * Removes a student from the collection and outputs the result to the output TextArea.
     *
     * @param student the student to remove
     */
    private void removeStudent(Student student) {
        if (student != null) {
            if (roster.remove(student)) {
                output.appendText("\n" + "Student removed from the roster.");
            } else {
                output.appendText("\n" + "Student is not in the roster.");
            }
        }
    }

    /**
     * Attempts to pay tuition for a student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param student the student to update
     * @param amount  the amount to add
     * @param date    the payment date
     */
    private void payTuition(Student student, double amount, Date date) {
        if (amount <= 0) {
            output.appendText("\n" + Constants.INVALID_AMOUNT_MESSAGE);
        } else if (date == null || !date.isValid() || date.compareTo(new Date()) > 0 && date.compareTo(new Date("01/01/2021")) < 0) {
            output.appendText("\n" + "Payment date invalid.");
        } else {
            try {
                if (roster.payTuition(student, amount, date)) {
                    output.appendText("\n" + "Payment applied.");
                } else {
                    output.appendText("\n" + "Student not in the roster.");
                }
            } catch (Exception e) {
                output.appendText("\n" + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Attempts to set study abroad status for a student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param student the student to update
     * @param status  the new study abroad status
     */
    private void setStudyAbroad(Student student, boolean status) {
        try {
            if (roster.setStudyAbroad(student, status)) {
                output.appendText("\n" + "Tuition updated.");
            } else {
                output.appendText("\n" + "Couldn't find the international student.");
            }
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Attempts to set financial aid amount for a resident student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param student the student to update
     * @param amount  the new financial aid amount for the student
     */
    private void setFinancialAid(Student student, double amount) {
        if (amount <= 0 || amount > TuitionConfig.MAX_FIN_AID) {
            output.appendText("\n" + Constants.INVALID_AMOUNT_MESSAGE);
        } else {
            try {
                if (roster.setFinancialAid(student, amount)) {
                    output.appendText("\n" + "Tuition updated.");
                } else {
                    output.appendText("\n" + "Student not in the roster.");
                }
            } catch (Exception e) {
                output.appendText("\n" + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Displays the students passed in using the studentView listView.
     *
     * @param students Student[] of students to display
     */
    private void setStudents(Student[] students) {
        String[] studentStrings = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            studentStrings[i] = students[i].toString();
        }
        for (String s : studentStrings) System.out.println(s);
        studentView.setItems(FXCollections.observableArrayList(studentStrings));
    }

    /**
     * Gets an unordered array of students from the roster and displays them.
     */
    private void showRosterUnordered() {
        try {
            setStudents(roster.getRoster());
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Gets an array of students ordered by name from the roster and displays them.
     */
    private void showRosterByStudentName() {
        try {
            setStudents(roster.getRosterByStudentName());
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Gets an array of students ordered by payment date from the roster and displays them.
     */
    private void showRosterByPaymentDate() {
        try {
            setStudents(roster.getPaymentStudentsByPaymentDate());
        } catch (Exception e) {
            output.appendText("\n" + e.getLocalizedMessage());
        }
    }
}