package se.domain.project.mazeitems;

public final class Player extends MazeItem {
  private String name;
  private int damage;
  private int health;
  private int positionX;
  private int positionY;

  private Player(String name, int damage, int health, int positionX, int positionY) {
    this.name = name;
    this.damage = damage;
    this.health = health;
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public static Player of(String name, int damage, int health, int positionX, int positionY) {
    return new Player(name, damage, health, positionX, positionY);
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
