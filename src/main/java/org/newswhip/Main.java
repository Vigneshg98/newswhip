package org.newswhip;

import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        URLStorage storage = new URLStorage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("URL Manager - Enter commands (ADD, REMOVE, EXPORT, EXIT):");

        // The program flow starts here, based on the user input command.
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("EXIT")) {
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length > 0) {
                String command = parts[0].toUpperCase();
                try {
                    switch (command) {
                        case "ADD":
                            if (parts.length == 3) {
                                String url = parts[1];
                                int score = Integer.parseInt(parts[2]);
                                storage.addURL(url, score);
                                System.out.println("URL added.");
                            } else {
                                System.out.println("Invalid ADD command. Usage: ADD <url> <score>");
                            }
                            break;
                        case "REMOVE":
                            if (parts.length == 2) {
                                String url = parts[1];
                                storage.removeURL(url);
                                System.out.println("URL removed.");
                            } else {
                                System.out.println("Invalid REMOVE command. Usage: REMOVE <url>");
                            }
                            break;
                        case "EXPORT":
                            System.out.println(storage.exportStatistics());
                            break;
                        default:
                            System.out.println("Unknown command. Available commands: ADD, REMOVE, EXPORT, EXIT");
                    }
                } catch (URISyntaxException | NumberFormatException e) {
                    System.out.println("Error processing command: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
