public class Cursor {
  private int x;
  private int y;
  private int maxX;
  private int maxY;

  public Cursor(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
    this.x = this.maxX / 2 - 15;
    this.y = this.maxY / 2 - 8;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x += x;
  }

  public void setY(int y) {
    this.y += y;
  }

  public void resetX() {
    this.x = this.maxX / 2 - 15;
  }

  public void resetY() {
    this.y = this.maxY / 2 - 8;
  }
}
