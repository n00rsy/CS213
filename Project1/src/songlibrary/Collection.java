package songlibrary;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    private int find(Album album) {
        for(int i = 0; i < numAlbums; i++) {
            if(album.equals(albums[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    //find the album index, or return NOT_FOUND
    private void grow() {
        int growthFactor = 4;

        Album[] newAlbums = new Album[albums.length + growthFactor];

        for(int i = 0; i < numAlbums; i++) {
            newAlbums[i] = albums[i];
        }
        albums = newAlbums;

    } //increase the capacity of the array list by 4

    public boolean add(Album album) {
        if (numAlbums == albums.length) {
            return false;
        }

        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }

    public boolean remove(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        }

        for (int i = albumIndex; i < numAlbums; i++) {
            albums[i] = albums[i+1];
        }

        numAlbums--;
        return true;
    }

    public boolean lendingOut(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        }
        else {
            albums[albumIndex].setAvailable(false);
            return true;
        }
    } //set to not available

    public boolean returnAlbum(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        }
        else {
            albums[albumIndex].setAvailable(true);
            return true;
        }
    } //set to available

    public void print() {
        ArrayUtil.print(albums, 0, numAlbums);
    } //display the list without specifying the order

    public void printByReleaseDate() {
        Album[] trimmedAlbums = (Album[]) ArrayUtil.copy(albums, 0, numAlbums);
        ArrayUtil.insertionSort(trimmedAlbums, (o1, o2) -> {
            Album a1 = (Album) o1;
            Album a2 = (Album) o2;
            return a1.getReleaseDate().compareTo(a2.getReleaseDate());
        });
        ArrayUtil.print(trimmedAlbums, 0, numAlbums);
    }

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