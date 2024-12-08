import jline.console.ConsoleReader;
import jline.console.KeyMap;
import jline.console.CursorBuffer;
import jline.console.UserInterruptException;
import jline.console.completer.Completer;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TerminalBoard {
  private ConsoleReader console;
  private Cursor cursor;

  public TerminalBoard() throws IOException {
    console = new ConsoleReader();
    cursor = new Cursor();
  }

  private void horizontalLine() throws IOException {
    // Horizontal boundary line
    cursor.setX(1);
    console.printf("%s", "-".repeat(29));
    cursor.setY(1);
  }

  private void verticalLine(String side) throws IOException {
    // Vertical boundary line
    if (side.equals("L")) {
      console.printf("|");
      cursor.setX(1);
    } else if (side.equals("R")) {
      console.printf("|");
      cursor.setY(1);
    } else {
      throw new IllegalArgumentException("Invalid value for argument 'side'");
    }
  }

  private String getProgress(int[][] board) {
    // Return the progress of the algorithm
    int visitedCellCount = 0;
    int totalCellCount = 64;

    for (int[] row : board) {
      for (int cell : row) {
        if (cell == 1) {
          visitedCellCount++;
        }
      }
    }

    double progress = ((double) visitedCellCount / totalCellCount) * 100;

    return String.format("%.2f%%", progress);
  }

  private void updateBoard(int[][] board) throws IOException {
    // Paint the updated board on the window
    cursor.resetX();
    cursor.resetY();
    printBoard(board, true);
  }

  private void printProgressBar(int[][] board) throws IOException {
    // Paint the progress bar on the window
    cursor.setX(-cursor.getX());
    cursor.setY(-cursor.getY());
    console.printf("Completed: ");
    console.printf("%s", " ".repeat(cursor.getMaxX() - 11));
    console.printf("%s", getProgress(board));
  }

  private void printBoard(int[][] board, boolean progress) throws IOException {
    // Paint the board on the window
    horizontalLine();

    for (int row = 0; row < 8; row++) {
      cursor.resetX();

      if (row > 0 && row <= 7) {
        console.printf("|%s|", " ".repeat(29));
        cursor.setY(1);
      }

      // Paint left border
      verticalLine("L");

      for (int col = 0; col < 8; col++) {
        // Cell is visited
        if (board[row][col] == 1) {
          console.printf("%d", board[row][col]);
        }

        // Knights cell
        else if (board[row][col] == 2) {
          console.printf("%d", board[row][col]);
        }

        // Cell is unvisited
        else {
          console.printf("%d", board[row][col]);
        }

        cursor.setX(1);

        if (col != 7) {
          cursor.setX(3);
        }
      }

      // Print right border
      verticalLine("R");
    }

    cursor.resetX();
    horizontalLine();
    
    if (progress) {
      // Print the progress bar
      printProgressBar(board);
    }

    console.refresh();

    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) {
    try {
      TerminalBoard terminalBoard = new TerminalBoard();
      int[][] board = new int[8][8];
      terminalBoard.printBoard(board, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class Cursor {
    private int x = 0;
    private int y = 0;

    // Assuming terminal width of 80 columns
    private final int maxX = 80;

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public void resetX() {
      this.x = 0;
    }

    public void resetY() {
      this.y = 0;
    }

    public int getMaxX() {
      return maxX;
    }
  }
}
