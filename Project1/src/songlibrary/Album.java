package songlibrary;

public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album other = (Album) obj;
        return title.equals(other.title) && artist.equals(other.artist);
    }

    @Override
    public String toString() {
        String separator = "::";
        String isAvailableString = isAvailable ? "is available" : "is not available";
        return "";
    }
}