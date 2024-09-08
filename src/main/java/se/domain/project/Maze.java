package se.domain.project;

import se.domain.project.mazeitems.*;

import java.util.Random;

public class Maze {
  private final MazeItem[][] mazeGrid;
  private static final Random random = new Random();

  private static final Monster[] monsterArray = {
          Monster.of(10, 20, 0, 0),
          Monster.of(12, 15, 0, 0),
          Monster.of(15, 10, 0, 0),
          Monster.of(8, 25, 0, 0),
          Monster.of(18, 12, 0, 0),
          Monster.of(20, 8, 0, 0),
          Monster.of(7, 30, 0, 0),
          Monster.of(14, 14, 0, 0),
          Monster.of(16, 11, 0, 0),
          Monster.of(9, 20, 0, 0)
  };

  private static final Treasure[] treasureArray = {
          Treasure.of(0, 0, "Shield of Glory", 10, "health"),
          Treasure.of(0, 0, "Sword of Might", 10, "damage"),
          Treasure.of(0, 0, "Ruby", 20, "health"),
          Treasure.of(0, 0, "Elixir of Strength", 15, "damage"),
          Treasure.of(0, 0, "Helmet of Wisdom", 5, "health"),
          Treasure.of(0, 0, "Amulet of Protection", 25, "damage"),
          Treasure.of(0, 0, "Gold Ring", 10, "health"),
          Treasure.of(0, 0, "Crown of Valor", 30, "damage"),
          Treasure.of(0, 0, "Potion of Vitality", 8, "health"),
          Treasure.of(0, 0, "Cape of Agility", 12, "damage")
  };

  private static final Upgrade[] upgradeArray = {
          Upgrade.of(0, 0, true, false, 10),
          Upgrade.of(0, 0, false, true, 5),
          Upgrade.of(0, 0, true, false, 15),
          Upgrade.of(0, 0, false, true, 10),
          Upgrade.of(0, 0, true, false, 20),
          Upgrade.of(0, 0, false, true, 12),
          Upgrade.of(0, 0, true, false, 25),
          Upgrade.of(0, 0, false, true, 8),
          Upgrade.of(0, 0, true, false, 30),
          Upgrade.of(0, 0, false, true, 7)
  };

  public Maze(char[][] blueprint) {
    this.mazeGrid = new MazeItem[blueprint.length][blueprint[0].length];
    buildMaze(blueprint);
  }

  private void buildMaze(char[][] blueprint) {
    for (int y = 0; y < blueprint.length; y++) {
      for (int x = 0; x < blueprint[y].length; x++) {
        char symbol = blueprint[y][x];
        mazeGrid[y][x] = createMazeItemFromSymbol(symbol, x, y);
      }
    }
  }

  private MazeItem createMazeItemFromSymbol(char symbol, int x, int y) {
    return switch (symbol) {
      case '#' -> Wall.of(x, y);
      case 'P' -> Player.of("Hero", 10, 100, x, y);
      case 'M' -> getRandomMonster(x, y);
      case 'U' -> getRandomUpgrade(x, y);
      case 'T' -> getRandomTreasure(x, y);
      case 'E' -> Exit.of(x, y);
      default -> null;
    };
  }

  private Monster getRandomMonster(int x, int y) {
    Monster monster = monsterArray[random.nextInt(monsterArray.length)];
    monster.setPositionX(x);
    monster.setPositionY(y);
    return monster;
  }

  private Treasure getRandomTreasure(int x, int y) {
    Treasure treasure = treasureArray[random.nextInt(treasureArray.length)];
    treasure.setPositionX(x);
    treasure.setPositionY(y);
    return treasure;
  }

  private Upgrade getRandomUpgrade(int x, int y) {
    Upgrade upgrade = upgradeArray[random.nextInt(upgradeArray.length)];
    upgrade.setPositionX(x);
    upgrade.setPositionY(y);
    return upgrade;
  }

  public void displayMaze() {
    for (MazeItem[] mazeItems : mazeGrid) {
      for (MazeItem mazeItem : mazeItems) {
        switch (mazeItem) {
          case Wall wall -> System.out.print("# ");
          case Player player -> System.out.print("P ");
          case Monster monster -> System.out.print("M ");
          case Upgrade upgrade -> System.out.print("U ");
          case Treasure treasure -> System.out.print("T ");
          case Exit exit -> System.out.print("E ");
          case null, default -> System.out.print(". ");
        }
      }
      System.out.println();
    }
  }

  public MazeItem[][] getMazeGrid() {
    return mazeGrid;
  }
}
