package songlibrary;

/**
 * A model class containing information about Albums.
 *
 * @author Noor
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    /**
     * Album constructor
     *
     * @param title
     * @param artist
     * @param genre
     * @param releaseDate
     * @param isAvailable
     */
    public Album(String title, String artist, Genre genre, Date releaseDate, boolean isAvailable) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    /**
     * Album title accessor
     *
     * @return album title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Album title mutator
     *
     * @param title new album title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Album artist accessor
     *
     * @return album artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Album artist mutator
     *
     * @param artist new album artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Album genre accessor
     *
     * @return album genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Album genre mutator
     *
     * @param genre new album genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Album release date accessor
     *
     * @return album release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Album release date mutator
     *
     * @param releaseDate new album release date
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Album availability accessor
     *
     * @return true if album is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Album availability mutator
     *
     * @param available new availibility
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Checks if another Album is the same as this Album.
     *
     * @param obj other object
     * @return true if objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album other = (Album) obj;
        return title.equals(other.title) && artist.equals(other.artist);
    }

    /**
     * Converts this Album object to string representation.
     *
     * @return string representation of this Album object
     */
    @Override
    public String toString() {
        String separator = "::";
        String isAvailableToString = isAvailable ? "is available" : "is not available";

        return title + separator +
                artist + separator +
                genre.toString() + separator +
                releaseDate.toString() + separator +
                isAvailableToString;
    }
}