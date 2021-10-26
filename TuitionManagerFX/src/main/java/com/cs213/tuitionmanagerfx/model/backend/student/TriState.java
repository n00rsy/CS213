package com.cs213.tuitionmanagerfx.model.backend.student;

import com.cs213.tuitionmanagerfx.model.config.Constants;
import com.cs213.tuitionmanagerfx.model.config.TuitionConfig;
import com.cs213.tuitionmanagerfx.model.enums.Location;
import com.cs213.tuitionmanagerfx.model.enums.Major;

/**
 * A model class representing a TriState student.
 *
 * @author Noor, Umar
 */
public class TriState extends NonResident {

    private Location location;
    private double triStateDiscount;

    /**
     * TriState constructor
     *
     * @param name
     * @param major
     * @param numCredits
     * @param location
     */
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

    /**
     * Location accessor
     *
     * @return this TriState's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Location mutator
     *
     * @param location new TriState location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * TriState discount accessor
     *
     * @return
     */
    public double getTriStateDiscount() {
        return triStateDiscount;
    }

    public void setTriStateDiscount(double triStateDiscount) {
        this.triStateDiscount = triStateDiscount;
    }

    @Override
    public void tuitionDue() {
        super.tuitionDue();
        if (!isPartTime())
            setTuitionDueAmount(getTuitionDueAmount() - getTriStateDiscount());
    }

    /**
     * Converts this NonResident object to string using the superclass toString method
     *
     * @return string representation of this TriState object
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state)" + Constants.OUTPUT_SEPARATOR + getLocation().toString();
    }
}
