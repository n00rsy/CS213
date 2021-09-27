package songlibrary.controller;

import songlibrary.models.Album;
import songlibrary.models.Collection;
import songlibrary.util.Constants;

import java.util.Scanner;

/**
 * The user interface class that manages the collection.
 *
 * @author Umar
 */
public class CollectionManager {

    /**
     * Contains the main logic loop that takes user input and interacts with the collection.
     */
    public void run() {

        Collection collection = new Collection();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("Collection Manager starts running.");

        while (running) {

            String input = sc.nextLine();
            int firstComma = input.indexOf(Constants.INPUT_REGEX);
            int cmdIndex = (firstComma > 0) ? firstComma : input.length();

            if (cmdIndex > 0) {

                String command = input.substring(0, cmdIndex);

                switch (command) {

                    case "A":
                        addAlbum(input, cmdIndex, collection);
                        break;

                    case "D":
                        removeAlbum(input, cmdIndex, collection);
                        break;

                    case "L":
                        lendOutAlbum(input, cmdIndex, collection);
                        break;

                    case "R":
                        returnAlbum(input, cmdIndex, collection);
                        break;

                    case "P":
                        collection.print();
                        break;

                    case "PD":
                        collection.printByReleaseDate();
                        break;

                    case "PG":
                        collection.printByGenre();
                        break;

                    case "Q":
                        running = false;
                        System.out.println("Collection Manager terminated.");
                        break;

                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    /**
     * Adds an album to the collection and outputs the result to the command line.
     * Checks to ensure album doesn't already exist and that the input date is valid.
     *
     * @param input The string to parse for the album.
     * @param cmdIndex The index of the command in the input string.
     * @param collection The collection to attempt to add the new album to.
     */
    private void addAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));

        if (album.getReleaseDate() != null && !album.getReleaseDate().isValid()) {
            System.out.println("Invalid Date!");
        } else if (collection.add(album)) {
            System.out.println(album.toString() + Constants.separator + "added.");
        } else {
            System.out.println(album.toString() + Constants.separator + "is already in the collection.");
        }
    }

    /**
     * Removes an album from the collection and outputs the result to the command line.
     *
     * @param input The string to parse for the album.
     * @param cmdIndex The index of the command in the input string.
     * @param collection The collection to attempt to remove the album from.
     */
    private void removeAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));

        if (collection.remove(album)) {
            System.out.println(album.toString() + Constants.separator + "deleted.");
        } else {
            System.out.println(album.toString() + Constants.separator + "is not in the collection.");
        }
    }

    /**
     * Attempts to lend out an album and outputs the result to the command line.
     *
     * @param input The string to parse for the album.
     * @param cmdIndex The index of the command in the input string.
     * @param collection The collection to attempt to find the album in.
     */
    private void lendOutAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));
        if (collection.lendingOut(album)) {
            System.out.println(album + Constants.separator + "lending out and set to not available.");
        } else {
            System.out.println(album + Constants.separator + "is not available.");
        }
    }

    /**
     * Attempts to return an album and outputs the result to the command line.
     *
     * @param input The string to parse for the album.
     * @param cmdIndex The index of the command in the input string.
     * @param collection The collection to attempt to find the album in.
     */
    private void returnAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));
        if (collection.returnAlbum(album)) {
            System.out.println(album + Constants.separator + "returning and set to available.");
        } else {
            System.out.println(album + Constants.separator + "return cannot be completed.");
        }
    }
}
