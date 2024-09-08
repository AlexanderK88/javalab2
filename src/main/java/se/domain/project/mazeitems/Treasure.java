package se.domain.project.mazeitems;

public final class Treasure extends Item {
  private final String name;
  private final int value;
  private final String effect;

  private Treasure(int positionX, int positionY, String name, int value, String effect) {
    this.setPositionX(positionX);
    this.setPositionY(positionY);
    this.name = name;
    this.value = value;
    this.effect = effect;
  }

  public static Treasure of(int positionX, int positionY, String name, int value, String effect) {
    return new Treasure(positionX, positionY, name, value, effect);
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }

  public String getEffect() {
    return effect;
  }

  public void addToInventory(Player player) {
    switch (effect) {
      case "health" -> player.setHealth(player.getHealth() + value);
      case "damage" -> player.setDamage(player.getDamage() + value);
    }
  }
}
