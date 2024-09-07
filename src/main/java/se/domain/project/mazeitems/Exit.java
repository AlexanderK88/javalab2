package se.domain.project.mazeitems;

public final class Exit extends Item {
  private Exit(int positionX, int positionY) {
    this.setPositionX(positionX);
    this.setPositionY(positionY);
  }

  public static Exit of(int positionX, int positionY) {
    return new Exit(positionX, positionY);
  }

  public void triggerExit(Player player) {
    System.out.println(player.getName() + ", you have reached the exit! You WON!!!");
  }
}