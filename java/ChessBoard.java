public class ChessBoard {
  public void printBoard() {
    for (int row = 0; row < 8; row++) {
      System.out.println("");
      System.out.println("---------------------------------");

      for (int column = 0; column < 9; column++) {
        System.out.print("| " + " " + " ");
      }
    }

    System.out.println("");
    System.out.println("---------------------------------");
  }
}
