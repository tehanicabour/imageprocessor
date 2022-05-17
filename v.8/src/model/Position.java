package model;

/**
 * Position Class for pixels.
 */
public class Position {

  private final int x;
  private final int y;

  /**
   * Constructs a Position given an x-coordinate and y-coordinate.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }


  /**
   * Gets the x-coordinate of the Position.
   *
   * @return x-coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-coordinate of the Position.
   *
   * @return y-coordinate
   */
  public int getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Position) {
      Position compare = (Position) obj;
      return compare.getX() == this.x && compare.getY() == this.y;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}