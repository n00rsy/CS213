package studentmanager.test;

import org.junit.Assert;
import org.junit.Test;
import studentmanager.Major;
import studentmanager.Roster;
import studentmanager.student.Student;

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
    public void addDuplicateStudentFromRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new Student("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new Student("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateInternationalFromRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new International("Umar Khattak", Major.CS, 12, false)));
        Assert.assertFalse(roster.add(new International("Umar Khattak", Major.CS, 12, false)));
    }

    @Test
    public void addDuplicateResidentFromRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new Resident("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new Resident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateNonResidentFromRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new NonResident("Umar Khattak", Major.CS, 12)));
        Assert.assertFalse(roster.add(new NonResident("Umar Khattak", Major.CS, 12)));
    }

    @Test
    public void addDuplicateTriStateFromRosterTest() {
        Roster roster = new Roster();
        Assert.assertTrue(roster.add(new TriState("Umar Khattak", Major.CS, 12, Location.NY)));
        Assert.assertFalse(roster.add(new TriState("Umar Khattak", Major.CS, 12, Location.NY)));
    }

    //tests for

}
