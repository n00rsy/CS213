package studentmanager;

public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile)) {
            return  false;
        }
        Profile other = (Profile) o;
        return other.getName().equals(name) && other.getMajor().equals(major);
    }

    @Override
    public String toString() {
        return name + ", " + major.toString();
    }
}
