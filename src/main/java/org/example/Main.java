package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication auth = new Authentication();

        System.out.println("Choose an option:");
        System.out.println("1. Create a new user");
        System.out.println("2. Authenticate an existing user");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                System.out.print("Enter username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String newPassword = scanner.nextLine();
                System.out.print("Enter role: ");
                String newRole = scanner.nextLine();
                auth.createUser(newUsername, newPassword, newRole);
                System.out.println("User created successfully.");
                break;
            case 2:
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                String role = auth.authenticateUser(username, password);
                if (!role.equals("unauthenticated")) {
                    System.out.println("Authentication successful.");
                    // Perform actions based on user role
                    switch (role.toLowerCase()) {
                        case "manager":
                            System.out.println("Welcome, Manager! You can add, update, and delete products.");

                            // Add your logic for manager role here
                            break;
                        case "supervisor":
                            System.out.println("Welcome, Supervisor! You can update products.");
                            // Add your logic for supervisor role here
                            break;
                        case "general worker":
                            System.out.println("Welcome, General Worker! You can update products.");
                            // Add your logic for general worker role here
                            break;
                        default:
                            System.out.println("Role not recognized.");
                            break;
                    }
                } else {
                    System.out.println("Authentication failed.");
                }
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        scanner.close();
    }
}
