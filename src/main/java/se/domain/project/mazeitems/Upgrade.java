package se.domain.project.mazeitems;

public final class Upgrade extends Item {
  private final boolean healthUpgrade;
  private final boolean damageUpgrade;
  private final int upgradeValue;

  private Upgrade(int positionX, int positionY, boolean healthUpgrade, boolean damageUpgrade, int upgradeValue) {
    this.setPositionX(positionX);
    this.setPositionY(positionY);
    this.healthUpgrade = healthUpgrade;
    this.damageUpgrade = damageUpgrade;
    this.upgradeValue = upgradeValue;
  }

  public static Upgrade of(int positionX, int positionY, boolean healthUpgrade, boolean damageUpgrade, int upgradeValue) {
    return new Upgrade(positionX, positionY, healthUpgrade, damageUpgrade, upgradeValue);
  }

  public int getUpgradeValue() {
    return upgradeValue;
  }

  public void applyUpgrade(Player player) {
    if (healthUpgrade) {
      player.setHealth(player.getHealth() + upgradeValue);
    }
    if (damageUpgrade) {
      player.setDamage(player.getDamage() + upgradeValue);
    }
  }

  public String getEffect() {
    return healthUpgrade ? "health" : "damage";
  }

  public String getName() {
    return healthUpgrade ? "Health Upgrade" : "Damage Upgrade";
  }
}
