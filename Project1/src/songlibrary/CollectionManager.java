package songlibrary;

import java.util.Scanner;

public class CollectionManager {
    public void run() {

        Collection collection = new Collection();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        String separator = " >> ";

        System.out.println("Collection Manager starts running.");

        while (running) {

            String input = sc.nextLine();
            int firstComma = input.indexOf(Constants.INPUT_REGEX);
            int cmdIndex = (firstComma > 0) ? firstComma : input.length();

            if (cmdIndex > 0) {

                String command = input.substring(0, cmdIndex);
                Album album;

                switch (command) {

                    case "A":
                        album = new Album(input.substring(cmdIndex + 1));

                        if (!album.getReleaseDate().isValid()) {
                            System.out.println("Invalid Date!");
                        }
                        else if (collection.add(album)) {
                            System.out.println(album.toString() + separator + "added.");
                        }
                        else {
                            System.out.println(album.toString() + separator + "is already in the collection.");
                        }
                        break;

                    case "D":
                        album = new Album(input.substring(cmdIndex + 1));

                        if(collection.remove(album)) {
                            System.out.println(album.toString() + separator + "deleted");
                        }
                        else {
                            System.out.println(album.toString() + separator + "is not in the collection.");
                        }
                        break;

                    case "L":
                        album = new Album(input.substring(cmdIndex + 1));
                        collection.lendingOut(album);
                        break;

                    case "R":
                        album = new Album(input.substring(cmdIndex + 1));
                        collection.returnAlbum(album);
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
}
