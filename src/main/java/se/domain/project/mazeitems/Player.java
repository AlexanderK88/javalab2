package se.domain.project.mazeitems;

import se.domain.project.Game;
import se.domain.project.Maze;
import se.domain.project.interfaces.Movable;
import se.domain.project.interfaces.Obstacle;

import java.util.Objects;

public final class Player extends MazeItem implements Movable {
  private String name;
  private int damage;
  private int health;
  private int positionX;
  private int positionY;

  private Player(String name, int damage, int health, int positionX, int positionY) {
    this.name = name;
    this.damage = damage;
    this.health = health;
    this.setPositionX(positionX);
    this.setPositionY(positionY);
  }

  public static Player of(String name, int damage, int health, int positionX, int positionY) {
    return new Player(name, damage, health, positionX, positionY);
  }

  @Override
  public void move(String direction, Maze maze) {
    int currentX = getPositionX();
    int currentY = getPositionY();

    int newX = currentX;
    int newY = currentY;

    switch (direction.toLowerCase()) {
      case "w" -> newY--;
      case "s" -> newY++;
      case "d" -> newX++;
      case "a" -> newX--;
    }

    handleMove(newX, newY, maze);
  }

  public void handleMove(int newX, int newY, Maze maze) {
    if (newX < 0 || newX >= maze.getMazeGrid()[0].length || newY < 0 || newY >= maze.getMazeGrid().length) {
      System.out.println("Invalid move. You can't go outside the maze.");
      return;
    }

    MazeItem targetItem = maze.getMazeGrid()[newY][newX];

    switch (targetItem) {
      case Obstacle obstacle when !obstacle.isPassable() -> {
        if (targetItem instanceof Wall) {
          System.out.println("You walked into a wall.");
        } else if (targetItem instanceof Monster monster) {
          if (Game.engageCombat(this, monster, maze)) {
            movePlayer(newX, newY, maze);
          }
        }
      }
      case Upgrade upgrade -> {
        upgrade.applyUpgrade(this);
        movePlayer(newX, newY, maze);
        System.out.println(getName() + " received " + upgrade.getName() + " which increases " + upgrade.getEffect() + " by " + upgrade.getUpgradeValue());
        if (Objects.equals(upgrade.getEffect(), "damage")) {
          System.out.println("You now have " + getDamage() + " damage.");
        } else {
          System.out.println("You now have " + getHealth() + " health.");
        }
      }
      case Treasure treasure -> {
        treasure.addToInventory(this);
        movePlayer(newX, newY, maze);
        System.out.println(getName() + " equipped " + treasure.getName() + " which increases " + treasure.getEffect() + " by " + treasure.getValue());
        if (Objects.equals(treasure.getEffect(), "damage")) {
          System.out.println("You now have " + getDamage() + " damage.");
        } else {
          System.out.println("You now have " + getHealth() + " health.");
        }
      }
      case Exit exit -> {
        exit.triggerExit(this);
        Game.showEndMenu();
      }
      case null, default -> movePlayer(newX, newY, maze);
    }
  }

  public void movePlayer(int newX, int newY, Maze maze) {
    maze.getMazeGrid()[this.getPositionY()][this.getPositionX()] = null;
    this.setPositionY(newY);
    this.setPositionX(newX);
    maze.getMazeGrid()[newY][newX] = this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}
