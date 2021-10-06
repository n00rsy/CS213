package studentmanager.student;

import studentmanager.Major;
import studentmanager.config.Constants;

public class International extends NonResident {

    private final int additionalFees = 2650;
    private boolean studyAbroad;

    public International(String name, Major major, int numCredits) {
        super(name, major, numCredits);
    }

    @Override
    public void tuitionDue() {

    }

    @Override
    public String toString() {
        return super.toString() + Constants.OUTPUT_SEPARATOR + "international";
    }
}
