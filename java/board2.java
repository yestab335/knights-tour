import java.util.List;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.Cursor;
import org.jline.utils.Colors;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    try {
      Terminal terminal = TerminalBuilder.builder().build();
      terminal.enterRawMode();
      terminal.puts(Cursor.VI, Cursor.HIDE);
      terminal.flush();

      horizontalLine(Terminal, new Cursor());
      List<List<Integer>> board = initializeBoard();
      printBoard(terminal, new Cursor(), board, false, 0.5f, true);

      Thread.sleep(3000);

      knightTour(terminal, new Cursor(), board);

      terminal.puts(Cursor.VI, Cursor.SHOW);
      terminal.flush();
      terminal.close();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static List<List<Integer>> initializeBoard() {
    // Initialize An Empty Chess Board
    List<List<Integer>> board = new ArrayList<>(8);
    for (int i = 0; i < 8; i++) {
      List<Integer> row = new ArrayList<>(8);
      for (int j = 0; j < 8; j++) {
        row.add(0);
      }

      board.add(row);
    }

    return board;
  }

  public static void horizontalLine(Terminal terminal, Cursor cursor) throws IOException {
    // Horizontal Boundry Line
    cursor.setColumn(1);
    terminal.writer().println("-".repeat(29));
    cursor.setRow(1);
  }

  public static void verticalLine(Terminal terminal, Cursor cursor, String side) throws IOException {
    // Vertical Boundry Line
    if (side.equals("L")) {
      terminal.writer().println("|");
      cursor.setColumn(1);
    } else if (side.equals("R")) {
      terminal.writer().println("|");
      cursor.setRow(1);
    } else {
      throw new IllegalArgumentException("Invalid value for argument \'side\'");
    }
  }

  public static String getProgress(List<List<Integer>> board) {
    // Return The Progress Of The Algorithm
    int visitedCellCount = 0;
    int totalCellCount = 64;

    for (List<Integer> row : board) {
      visitedCellCount += Collections.frequency(row, 1);
    }

    int progress = (int) ((double) visitedCellCount / totalCellCount * 100);

    return progress + "%";
  }

  public static void updateBoard(Terminal terminal, Cursor cursor, List<List<Integer>> board) throws IOException {
    // Paint The Updated Board On The Window
    cursor.setColumn(1);
    cursor.setRow(1);
    printBoard(terminal, cursor, board, true, 0.5f, false);
  }

  public static void printProgressBar(Terminal terminal, Cursor cursor, List<List<Integer>> board) throws IOException {
    // Paint The Progress Bar On The Window
    cursor.setColumn(0);
    cursor.setRow(0);
    terminal.writer().print("Completed: ");
    terminal.writer().print(" ".repeat(cursor.getMaxColumn() - 11));
    terminal.writer().print(getProgress(board));
    terminal.writer().println(Colors.colorize("%", Colors.brightYellow()));
  }

  public static void printBoard(Terminal terminal, Cursor cursor, List<List<Integer>> board, boolean progress, float sleepValue, boolean initialize) throws IOException {
    // Paint The Board On The Window
    horizontalLine(terminal, cursor);
    for (int row = 0)
  }
}