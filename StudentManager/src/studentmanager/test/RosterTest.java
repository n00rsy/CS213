package studentmanager.test;

import org.junit.Assert;
import org.junit.Test;
import studentmanager.Roster;

import java.util.Calendar;

/**
 * JUnit test class for Roster.add and Roster.remove method
 *
 * @author Noor, Umar
 */
public class RosterTest {
    @Test
    public void removeFromEmptyRosterTest() {
        Roster roster = new Roster();
        Assert.assertFalse(roster.remove(new Student("Umar Khattak", Major.CS)));
    }
}
