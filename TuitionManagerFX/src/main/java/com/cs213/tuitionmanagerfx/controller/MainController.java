package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.implementation.backend.Date;
import com.cs213.tuitionmanagerfx.implementation.backend.Roster;
import com.cs213.tuitionmanagerfx.implementation.backend.student.Student;
import com.cs213.tuitionmanagerfx.implementation.config.Constants;
import com.cs213.tuitionmanagerfx.implementation.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.implementation.util.ParseUtil;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * The main controller for the project containing the roster instance and links to navigate the rest of the program.
 * @author Noor, Umar
 */
public class MainController {

    @FXML
    TextArea output;

    Roster roster;

    /**
     * Runs on load and initializes the roster.
     */
    public void initialize() {
        roster = new Roster();
    }

    /**
     * Handles the add student button click by switching to the add student scene.
     * @param event
     */
    @FXML
    private void handleAddButtonClick(ActionEvent event) {
        SceneManager.switchScene("/add-student.fxml",
                MainController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    /**
     * Handles the remove student button click by switching to the remove student scene.
     * @param event
     */
    @FXML
    private void handleRemoveButtonClick(ActionEvent event) {
        SceneManager.switchScene("/remove-student.fxml",
                MainController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    /**
     * Handles the print roster button click by switching to the print roster scene.
     * @param event
     */
    @FXML
    private void handlePrintButtonClick(ActionEvent event) {
        SceneManager.switchScene("/print-students-options.fxml",
                MainController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    /**
     * Adds a student to the collection and outputs the result to the output TextArea.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (student != null) {
            if (roster.add(student)) {
                output.setText("Student added.");
            } else {
                output.setText("Student is already in the roster.");
            }
        }
    }

    /**
     * Removes a student from the collection and outputs the result to the output TextArea.
     *
     * @param student the student to remove
     */
    public void removeStudent(Student student) {
        if (student != null) {
            if (roster.remove(student)) {
                output.setText("Student removed from the roster.");
            } else {
                output.setText("Student is not in the roster.");
            }
        }
    }

    /**
     * Calculates tuition for every student in the roster.
     * Outputs to output TextArea when complete.
     *
     * @param roster the roster to calculate tuition for
     */
    private void calculateTuition(Roster roster) {
        roster.calculateTuition();
        output.setText("Calculation completed.");
    }

    /**
     * Attempts to pay tuition for a student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param args   the cmd arguments to parse from the user
     * @param roster the roster to update
     */
    private void payTuition(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
            return;
        }
        if (args.length == 3) {
            output.setText("Payment amount missing.");

        } else if (args.length == 4) {
            output.setText("Missing data in output TextArea.");
        } else {
            double amount = 0;
            Date date = new Date(args[4]);
            try {
                amount = Double.parseDouble(args[3]);
            } catch (Exception e) {
                output.setText(Constants.INVALID_AMOUNT_MESSAGE);
                return;
            }
            if (amount <= 0) {
                output.setText(Constants.INVALID_AMOUNT_MESSAGE);
            } else if (date == null || !date.isValid() || date.compareTo(new Date()) > 0 && date.compareTo(new Date("01/01/2021")) < 0) {
                output.setText("Payment date invalid.");
            } else {
                try {
                    if (roster.payTuition(student, amount, date)) {
                        output.setText("Payment applied.");
                    } else {
                        output.setText("Student not in the roster.");
                    }
                } catch (Exception e) {
                    output.setText(e.getLocalizedMessage());
                }
            }
        }
    }

    /**
     * Attempts to set study abroad status for a student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param args   the cmd arguments to parse from the user
     * @param roster the roster to update
     */
    private void setStudyAbroad(String[] args, Roster roster) {
        Student student = null;
        boolean status = false;
        try {
            student = ParseUtil.parseStudent(args);
            status = ParseUtil.parseBoolean(args[3]);
            if (roster.setStudyAbroad(student, status)) {
                output.setText("Tuition updated.");
            } else {
                output.setText("Couldn't find the international student.");
            }
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
        }
    }

    /**
     * Attempts to set financial aid amount for a resident student and outputs results to cmd.
     * Checks to ensure that the input date is valid.
     *
     * @param args   the cmd arguments to parse from the user
     * @param roster the roster to update
     */
    private void setFinancialAid(String[] args, Roster roster) {
        Student student = null;
        try {
            student = ParseUtil.parseStudent(args);
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
            return;
        }
        if (args.length == 3) {
            output.setText("Missing the amount.");
        } else {
            double amount = 0;
            try {
                amount = Double.parseDouble(args[3]);
            } catch (Exception e) {
                output.setText(Constants.INVALID_AMOUNT_MESSAGE);
                return;
            }
            if (amount <= 0 || amount > TuitionConfig.MAX_FIN_AID) {
                output.setText(Constants.INVALID_AMOUNT_MESSAGE);
            } else {
                try {
                    if (roster.setFinancialAid(student, amount)) {
                        output.setText("Tuition updated.");
                    } else {
                        output.setText("Student not in the roster.");
                    }
                } catch (Exception e) {
                    output.setText(e.getLocalizedMessage());
                }
            }
        }
    }

    /**
     * Switches to the print student results scene and shows students in the input array.
     * @param stage the current stage
     * @param students an array of students to display
     */
    private void showRoster(Stage stage, Student[] students) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/print-students-result.fxml"
                    )
            );
            stage.setScene(new Scene(loader.load()));
            PrintStudentsResultController printStudentsResultController = loader.getController();
            printStudentsResultController.setStudents(students);

            stage.show();
        }catch (Exception e) {
            output.setText(e.toString());
        }
    }

    /**
     * Gets an unordered array of students from the roster and displays them.
     * @param stage the current stage.
     */
    public void showRosterUnordered(Stage stage) {
        try {
            showRoster(stage, roster.getRoster());
        }
        catch (Exception e) {
            output.setText(e.toString());
        }
    }

    /**
     * Gets an array of students ordered by name from the roster and displays them.
     * @param stage the current stage.
     */
    public void showRosterByStudentName(Stage stage) {
        try {
            showRoster(stage, roster.getRosterByStudentName());
        }
        catch (Exception e) {
            output.setText(e.toString());
        }
    }

    /**
     * Gets an array of students ordered by payment date from the roster and displays them.
     * @param stage the current stage.
     */
    public void showRosterByPaymentDate(Stage stage) {
        try {
            showRoster(stage, roster.getPaymentStudentsByPaymentDate());
        }
        catch (Exception e) {
            output.setText(e.toString());
        }
    }
}