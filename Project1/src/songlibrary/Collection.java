package songlibrary;

/**
 * A class that defines a mutable, dynamically resizable list of Albums that can be interacted with.
 *
 * @author Noor, Umar
 */
public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    /**
     * Initializes a new collection.
     */
    public Collection() {
        int initialSize = 4;
        albums = new Album[initialSize];
        numAlbums = 0;
    }

    /**
     * Iterates through this collection to find the index of the album, or returns NOT_FOUND.
     *
     * @param album the album to search for
     * @return index of the album, or NOT_FOUND if it is not found
     */
    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++) {
            if (album.equals(albums[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Increase the capacity of the inner array by 4.
     */
    private void grow() {
        int growthFactor = 4;

        Album[] newAlbums = new Album[albums.length + growthFactor];

        for (int i = 0; i < numAlbums; i++) {
            newAlbums[i] = albums[i];
        }
        albums = newAlbums;

    }

    /**
     * Attempts to add the album to the list.
     *
     * @param album the album to add
     * @return True if added, false otherwise.
     */
    public boolean add(Album album) {
        if (find(album) >= 0) {
            return false;
        }

        if (numAlbums == albums.length) {
            grow();
        }

        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }

    /**
     * Removes the album from the list.
     *
     * @param album the album to remove.
     * @return True if removed, false otherwise.
     */
    public boolean remove(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        }

        for (int i = albumIndex; i < numAlbums; i++) {
            albums[i] = albums[i + 1];
        }

        numAlbums--;
        return true;
    }

    /**
     * Finds the album in the array, then sets its availability to false.
     *
     * @param album the album to change
     * @return true if successful, false otherwise.
     */
    public boolean lendingOut(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        } else {
            albums[albumIndex].setAvailable(false);
            return true;
        }
    }

    /**
     * Finds the album in the array, then sets its availability to true.
     *
     * @param album the album to change
     * @return true if successful, false otherwise.
     */
    public boolean returnAlbum(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        } else {
            albums[albumIndex].setAvailable(true);
            return true;
        }
    }

    /**
     * Displays all albums to the output stream without specifying the order.
     */
    public void print() {
        ArrayUtil.print(albums, 0, numAlbums);
    }

    /**
     * Displays all albums to the output stream, sorted by release dates in ascending order
     */
    public void printByReleaseDate() {
        Album[] trimmedAlbums = (Album[]) ArrayUtil.copy(albums, 0, numAlbums);
        ArrayUtil.insertionSort(trimmedAlbums, (o1, o2) -> {
            Album a1 = (Album) o1;
            Album a2 = (Album) o2;
            return a1.getReleaseDate().compareTo(a2.getReleaseDate());
        });
        ArrayUtil.print(trimmedAlbums, 0, numAlbums);
    }

    /**
     * Displays all albums to the output stream, sorted by genres in ascending order
     */
    public void printByGenre() {
        Album[] trimmedAlbums = (Album[]) ArrayUtil.copy(albums, 0, numAlbums);
        ArrayUtil.insertionSort(trimmedAlbums, (o1, o2) -> {
            Album a1 = (Album) o1;
            Album a2 = (Album) o2;
            return a1.getGenre().compareTo(a2.getGenre());
        });
        ArrayUtil.print(trimmedAlbums, 0, numAlbums);
    }
}