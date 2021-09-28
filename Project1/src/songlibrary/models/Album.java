package songlibrary.models;

import songlibrary.util.Constants;

/**
 * A model class containing information about Albums.
 *
 * @author Noor, Umar
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
     * Builds up an Album object by parsing an input string containing a delimited list of arguments.
     *
     * @param details
     */
    public Album(String details) {
        String[] tokens = details.split(Constants.INPUT_REGEX);

        if (tokens.length >= 2) {
            setTitle(tokens[Constants.INPUT_TITLE_IDX]);
            setArtist(tokens[Constants.INPUT_ARTIST_IDX]);
        }

        if (tokens.length == 4) {
            setGenreByString(tokens[Constants.INPUT_GENRE_IDX]);
            setReleaseDate(new Date(tokens[Constants.INPUT_RELEASE_DATE_IDX]));
        }

        isAvailable = true;
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
     * Sets Album genre based on an input string.
     *
     * @param genreStr String representation of new album genre
     */
    private void setGenreByString(String genreStr) {
        if (genreStr.length() > 0) {
            genreStr = genreStr.substring(0, 1).toUpperCase() + genreStr.substring(1).toLowerCase();
            switch (genreStr) {
                case Constants.CLASSICAL:
                    genre = Genre.Classical;
                    break;
                case Constants.COUNTRY:
                    genre = Genre.Country;
                    break;
                case Constants.JAZZ:
                    genre = Genre.Jazz;
                    break;
                case Constants.POP:
                    genre = Genre.Pop;
                    break;
                default:
                    genre = Genre.Unknown;
                    break;
            }
        } else {
            genre = Genre.Unknown;
        }

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

        String albumStr = title + separator + artist;

        if (genre != null && releaseDate != null) {
            albumStr = albumStr + separator +
                    genre.toString() + separator +
                    releaseDate.toString() + separator +
                    isAvailableToString;
        }

        return albumStr;
    }
}