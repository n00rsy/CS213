package songlibrary;

import java.util.Arrays;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    private int find(Album album) {
        for(int i = 0; i < numAlbums; i++) {
            if(album.equals(albums[i])) {
                return i;
            }
        }
        return -1;
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
        //TODO: implement
        return true;
    }

    public boolean remove(Album album) {
        int albumIndex = find(album);
        if (albumIndex < 0) {
            return false;
        }
        //TODO: implement
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

    private void printAlbumArray (Album[] albums) {
        for(int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
    }

    public void print() {
        printAlbumArray(albums);
    } //display the list without specifying the order

    public void printByReleaseDate() {
    }

    public void printByGenre() {
    }
}