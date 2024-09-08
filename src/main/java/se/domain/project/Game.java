package se.domain.project;

import se.domain.project.mazeitems.Monster;
import se.domain.project.mazeitems.Player;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

  private int selectedBlueprint = 1;
  private final Scanner scanner = new Scanner(System.in);
  private Player player;
  private Maze maze;

  public void startGame() {
    if (maze == null) {
      char[][] blueprint = getBlueprint(selectedBlueprint);
      maze = new Maze(blueprint);
    }

    System.out.print("Enter your player's name: ");
    String playerName = scanner.nextLine();

    System.out.println("Starting the game with Blueprint " + selectedBlueprint + "...");
    initializePlayer(playerName);
    gameLoop();
  }

  public void chooseMap() {
    System.out.println("\n==== Choose Blueprint ====");
    System.out.println("1. Blueprint 1");
    System.out.println("2. Blueprint 2");
    System.out.println("3. Blueprint 3");
    System.out.print("Enter your choice: ");

    selectedBlueprint = scanner.nextInt();
    scanner.nextLine();
    if (selectedBlueprint < 1 || selectedBlueprint > 3) {
      System.out.println("Invalid blueprint choice. Defaulting to Blueprint 1.");
      selectedBlueprint = 1;
    } else {
      System.out.println("Blueprint " + selectedBlueprint + " loaded.");
      maze = null;
    }
  }

  private char[][] getBlueprint(int blueprintNumber) {
    return switch (blueprintNumber) {
      case 2 -> Blueprints.getBlueprint(2);
      case 3 -> Blueprints.getBlueprint(3);
      default -> Blueprints.getBlueprint(1);
    };
  }

  private void initializePlayer(String playerName) {
    for (int y = 0; y < maze.getMazeGrid().length; y++) {
      for (int x = 0; x < maze.getMazeGrid()[y].length; x++) {
        if (maze.getMazeGrid()[y][x] instanceof Player) {
          player = (Player) maze.getMazeGrid()[y][x];
          player.setName(playerName);
          break;
        }
      }
      if (player != null) break;
    }
    if (player == null) {
      throw new IllegalStateException("Player not found in the maze blueprint.");
    }
  }

  public void gameLoop() {
    boolean running = true;
    while (running) {
      System.out.println();
      maze.displayMaze();

      System.out.print("Enter move (w/a/s/d) or q to quit: ");
      String input = scanner.next();
      System.out.println();
      switch (input.toLowerCase()) {
        case "w", "a", "s", "d" -> player.move(input, maze);
        case "q" -> {
          running = false;
          System.out.println("Exiting the game...");
        }
        default -> System.out.println("Invalid input. Use 'w', 'a', 's', 'd', or 'q'.");
      }
      if (running) {
        moveMonsters();
      }
    }
    showEndMenu();
  }

  public static boolean engageCombat(Player player, Monster monster, Maze maze) {
    System.out.println("You encountered a monster!");

    if (player.getDamage() >= monster.getHealth()) {
      System.out.println("You defeated the monster with one strike!");
      maze.getMazeGrid()[monster.getPositionY()][monster.getPositionX()] = null;
    } else {
      int numberOfHits = (int) Math.ceil((double) monster.getHealth() / player.getDamage());
      int totalDamageToPlayer = numberOfHits * monster.getDamage();

      player.setHealth(player.getHealth() - totalDamageToPlayer);

      if (player.getHealth() <= 0) {
        System.out.println("The monster defeated you! Game Over.");
        showEndMenu();
        return false;
      } else {
        System.out.println("You defeated the monster but took " + totalDamageToPlayer + " damage.");
        System.out.println("You have " + player.getHealth() + " health left.");
        maze.getMazeGrid()[monster.getPositionY()][monster.getPositionX()] = null;
      }
    }
    return true;
  }

  private void moveMonsters() {
    Set<Monster> movedMonsters = new HashSet<>();
    for (int y = 0; y < maze.getMazeGrid().length; y++) {
      for (int x = 0; x < maze.getMazeGrid()[y].length; x++) {
        if (maze.getMazeGrid()[y][x] instanceof Monster monster) {
          if (monster.getHealth() > 0 && !movedMonsters.contains(monster)) {
            monster.move(null, maze);
            movedMonsters.add(monster);
          }
        }
      }
    }
  }

  public static void showEndMenu() {
    Scanner scanner = new Scanner(System.in);
    String choice;

    while (true) {
      System.out.println("\n==== End of Game ====");
      System.out.println("1. Return to Main Menu");
      System.out.println("2. Exit Game");
      System.out.print("Enter your choice: ");
      choice = scanner.nextLine().toLowerCase();

      switch (choice) {
        case "1" -> {
          Main.main(null);
          return;
        }
        case "2" -> {
          exitApplication();
          return;
        }
        default -> System.out.println("Invalid choice. Please enter 1 or 2.");
      }
    }
  }


  private static void exitApplication() {
    System.out.println("Exiting the application...");
    System.exit(0);
  }
}
