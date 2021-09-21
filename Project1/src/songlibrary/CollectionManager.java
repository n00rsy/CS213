package songlibrary;

import java.util.Scanner;

public class CollectionManager {
    public void run() {
        Collection collection = new Collection();
        System.out.println("Running!!!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Collection Manager starts running.");
        boolean running = true;
        while (running) {
            String input = sc.nextLine();
            String[] tokens = input.split(",");
            if (tokens.length > 0) {
                String command = tokens[0];
                Album album;
                switch (command) {
                    case "A":
                        album = new Album(input.substring(2));
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
                }
            } else {
                //TODO
            }
        }
    }
}
