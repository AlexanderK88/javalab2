package se.domain.project;

import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Game game = new Game();
    boolean isMenuVisible = true;

    while (isMenuVisible) {
      displayMenu();
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          game.startGame();
          isMenuVisible = false;
          break;
        case 2:
          game.chooseMap();
          break;
        case 3:
          exitApplication();
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }

  private static void displayMenu() {
    System.out.println("\n==== Main Menu ====");
    System.out.println("1. Start Game");
    System.out.println("2. Choose Map");
    System.out.println("3. Exit Application");
    System.out.print("Enter your choice: ");
  }

  private static void exitApplication() {
    System.out.println("Exiting the application...");
    System.exit(0);
  }
}