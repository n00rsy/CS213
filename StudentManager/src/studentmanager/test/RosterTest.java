package studentmanager.test;

import org.junit.Assert;
import org.junit.Test;
import studentmanager.enums.Location;
import studentmanager.enums.Major;
import studentmanager.implementation.Roster;
import studentmanager.implementation.student.*;

/**
 * JUnit test class for Roster.add and Roster.remove method
 *
 * @author Noor, Umar
 */
public class RosterTest {
    //tests for removing from empty roster
    @Test
    public void removeStudentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new Student("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void removeInternationalFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new International("Umar Khattak", Major.CS, 12, false)));
    }

    @Test
    public void removeResidentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new Resident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void removeNonResidentFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new NonResident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void removeTriStateFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new TriState("Umar Khattak", Major.CS, 12, Location.NY)));
    }
    //tests for adding duplicates
    @Test
    public void addDuplicateStudentToRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new Student("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new Student("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateInternationalToRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new International("Umar Khattak", Major.CS, 12, false)));
        Assert.assertFalse(roster.add(new International("Umar Khattak", Major.CS, 12, false)));
    }

    @Test
    public void addDuplicateResidentToRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new Resident("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new Resident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateNonResidentToRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new NonResident("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new NonResident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateTriStateToRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new TriState("Umar Khattak", Major.CS, 12, Location.NY)));
        Assert.assertFalse(roster.add(new TriState("Umar Khattak", Major.CS, 12, Location.NY)));
    }
}