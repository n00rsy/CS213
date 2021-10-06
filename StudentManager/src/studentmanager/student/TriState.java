package studentmanager.student;

import studentmanager.Major;
import studentmanager.config.Constants;

public class TriState extends NonResident {

    private String state;

    private int TriStateDiscount;

    public TriState(String name, Major major, int numCredits) {
        super(name, major, numCredits);
    }

    @Override
    public void tuitionDue() {

    }

    @Override
    public String toString() {
        return super.toString() + " (tri-state)"; // TODO: figure out ny/ ct stuff
    }
}
