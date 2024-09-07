package se.domain.project.mazeitems;

import se.domain.project.interfaces.Obstacle;

import java.util.Random;

public final class Monster extends Item implements Obstacle {
  private final int damage;
  private final int health;
  private static final Random random = new Random();

  private Monster(int positionX, int positionY, int damage, int health) {
    this.setPositionX(positionX);
    this.setPositionY(positionY);
    this.damage = damage;
    this.health = health;
  }

  public static Monster of(int positionX, int positionY, int damage, int health) {
    return new Monster(positionX, positionY, damage, health);
  }

  public int getDamage() {
    return damage;
  }

  public int getHealth() {
    return health;
  }

  @Override
  public boolean isPassable() {
    return false;
  }

}
