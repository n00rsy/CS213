package songlibrary;

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

    private void addAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));

        if (!album.getReleaseDate().isValid()) {
            System.out.println("Invalid Date!");
        }
        else if (collection.add(album)) {
            System.out.println(album.toString() + Constants.separator + "added.");
        }
        else {
            System.out.println(album.toString() + Constants.separator + "is already in the collection.");
        }
    }

    private void removeAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));

        if(collection.remove(album)) {
            System.out.println(album.toString() + Constants.separator + "deleted.");
        }
        else {
            System.out.println(album.toString() + Constants.separator + "is not in the collection.");
        }
    }

    private void lendOutAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));
        if(collection.lendingOut(album)) {
            System.out.println(album + Constants.separator + "lending out and set to not available.");
        }
        else {
            System.out.println(album + Constants.separator + "is not available.");
        }
    }

    private void returnAlbum(String input, int cmdIndex, Collection collection) {
        Album album = new Album(input.substring(cmdIndex + 1));
        if(collection.returnAlbum(album)) {
            System.out.println(album + Constants.separator + "returning and set to available.");
        }
        else {
            System.out.println(album + Constants.separator + "return cannot be completed.");
        }
    }
}
