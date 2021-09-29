package studentmanager;

public abstract class Student {

    private Profile profile;

    public void tuitionDue() {

    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "";
    }
}
