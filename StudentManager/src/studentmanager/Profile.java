package studentmanager;

/**
 * A model class representing a student profile.
 * Used to uniquely identify students in the roster.
 *
 * @author Noor, Umar
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Profile constructor
     *
     * @param name
     * @param major
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Profile name accessor
     * @return this Profile's name
     */
    public String getName() {
        return name;
    }

    /**
     * Profile major accessor
     * @return this Profile's major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Checks if this Profile is equal to another object.
     * @param o other
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) o;
        return other.getName().equals(name) && other.getMajor().equals(major);
    }

    /**
     * Converts this Profile to String representation.
     * @return the string representation of this Profile.
     */
    @Override
    public String toString() {
        return name + ", " + major.toString();
    }
}
