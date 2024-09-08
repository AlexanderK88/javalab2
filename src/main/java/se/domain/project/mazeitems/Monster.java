package se.domain.project.mazeitems;

import se.domain.project.Maze;
import se.domain.project.Game;
import se.domain.project.interfaces.Movable;
import se.domain.project.interfaces.Obstacle;

import java.util.Random;

public final class Monster extends Item implements Obstacle, Movable {
  private final int damage;
  private final int health;
  private static final Random random = new Random();

  private Monster(int damage, int health, int positionX, int positionY) {
    this.damage = damage;
    this.health = health;
    this.setPositionX(positionX);
    this.setPositionY(positionY);
  }

  public static Monster of( int damage, int health, int positionX, int positionY) {
    return new Monster(damage, health, positionX, positionY);
  }

  @Override
  public boolean isPassable() {
    return false;
  }

  @Override
  public void move(String direction, Maze maze) {
    int currentX = getPositionX();
    int currentY = getPositionY();

    int newX = currentX;
    int newY = currentY;

    int moveDirection = random.nextInt(4);
    switch (moveDirection) {
      case 0 -> newY = currentY - 1;
      case 1 -> newY = currentY + 1;
      case 2 -> newX = currentX - 1;
      case 3 -> newX = currentX + 1;
    }

    if (isValidMove(newX, newY, maze)) {
      MazeItem targetItem = maze.getMazeGrid()[newY][newX];

      if (targetItem instanceof Player player) {
        Game.engageCombat(player, this, maze);
      } else if (targetItem == null) {
        maze.getMazeGrid()[currentY][currentX] = null;
        setPositionX(newX);
        setPositionY(newY);
        maze.getMazeGrid()[newY][newX] = this;
      }
    }
  }

  private boolean isValidMove(int x, int y, Maze maze) {
    return x >= 0 && x < maze.getMazeGrid()[0].length &&
            y >= 0 && y < maze.getMazeGrid().length &&
            (maze.getMazeGrid()[y][x] == null || maze.getMazeGrid()[y][x] instanceof Player);
  }

  public int getDamage() {
    return damage;
  }

  public int getHealth() {
    return health;
  }
}
