package se.domain.project.mazeitems;

public abstract class Item {
  private int positionX;
  private int positionY;

  public Item(int positionX, int positionY) {
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
