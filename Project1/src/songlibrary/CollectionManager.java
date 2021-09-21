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
            String[] tokens = input.split(",");

            if (tokens.length > 0) {

                String command = tokens[0];
                Album album;

                switch (command) {

                    case "A":
                        album = new Album(input.substring(2));
                        if (collection.add(album)) {
                            System.out.println(album.toString() + separator + "added.");
                        }
                        else {
                            System.out.println(album.toString() + separator + "is already in the collection.");
                        }
                        collection.add(album);
                        break;

                    case "D":
                        album = new Album(input.substring(2));
                        collection.remove(album);
                        break;

                    case "L":
                        album = new Album(input.substring(2));
                        collection.lendingOut(album);
                        break;

                    case "R":
                        album = new Album(input.substring(2));
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
