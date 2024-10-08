package se.domain.project.mazeitems;

public abstract sealed class Item extends MazeItem permits Exit, Monster, Treasure, Upgrade, Wall {
  private int positionX;
  private int positionY;

  private Item(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  protected Item() {
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}
