package studentmanager.student;

import studentmanager.Location;
import studentmanager.Major;
import studentmanager.config.Constants;
import studentmanager.config.TuitionConfig;

public class TriState extends NonResident {

    private Location location;
    private double triStateDiscount;

    public TriState(String name, Major major, int numCredits, Location location) {
        super(name, major, numCredits);
        setLocation(location);
        switch (location) {
            case NY:
                setTriStateDiscount(TuitionConfig.NY_DISCOUNT);
                break;
            case CT:
                setTriStateDiscount(TuitionConfig.CT_DISCOUNT);
                break;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getTriStateDiscount() {
        return triStateDiscount;
    }

    public void setTriStateDiscount(double triStateDiscount) {
        this.triStateDiscount = triStateDiscount;
    }

    @Override
    public void tuitionDue() {
        super.tuitionDue();
        setTuitionDueAmount(getTuitionDueAmount() - getTriStateDiscount());
    }

    @Override
    public String toString() {
        return super.toString() + "(tri-state)" + Constants.OUTPUT_SEPARATOR + location.toString();
    }
}
