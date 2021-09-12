package songlibrary;

public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;

    public Album(String title, String artist, Genre genre, Date releaseDate, boolean isAvailable) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    private boolean isAvailable;

    private String isAvailableToString() {
        return isAvailable ? "is available" : "is not available";
    }

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

        return  title + separator +
                artist + separator +
                genre.toString() + separator +
                releaseDate.toString() + separator +
                isAvailableToString();
    }
}