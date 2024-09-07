package se.domain.project.mazeitems;

import se.domain.project.interfaces.Obstacle;

public final class Wall extends Item implements Obstacle {
  private Wall(int positionX, int positionY) {
    this.setPositionX(positionX);
    this.setPositionY(positionY);
  }

  public static Wall of(int positionX, int positionY) {
    return new Wall(positionX, positionY);
  }

  @Override
  public boolean isPassable() {
    return false;
  }
}
