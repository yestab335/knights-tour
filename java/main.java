/**
 * Main
*/
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  private static final int BOARD_SIZE = 8;
  private static final int[] DX = {1, 2, 2, 1, -1, -2, -2, -1};
  private static final int[] DY = {-2, -1, 1, 2, 2, 1, -1, -2};
  public static void main(String args[]) {
    // Ensure terminal size is sufficient
    if (System.console() == null) {
      System.err.println("No console available.");
      System.exit(1);
    }

    clearConsole();
    printDummyBoard();

    Scanner sc = new Scanner(System.in);

    int[] position = null;

    while (true) {
      System.out.print("Knight's position (row, col): ");
      String input = sc.nextLine();

      position = parsePosition(input);

      if (position != null) {
        break;
      } else {
        // Red text
        System.out.println("\\033[31mInvalid, Try Again.\\033[0m");

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    // Start visualization
    visualize(position);
    clearConsole();
    sc.close();
  }

  private static void clearConsole() {
    String os = System.getProperty("os.name").toLowerCase();

    try {
      if (os.contains("win")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void printDummyBoard() {
    // Print dummy board with indexes for all the cells
    int j = 0;

    for (int i = 0; i < 9; i++) {
      if (i != 0) {
        System.out.println(j + "   " + "\033[33m|   |   |   |   |   |   |   |   |");
        System.out.println("    " + "\033[33m---------------------------------");
        j++;
      } else {
        System.out.println("      0   1   2   3   4   5   6   7");
        System.out.println("    " + "\033[33m---------------------------------");
      }
    }

    System.out.println();
  }

  private static int[] parsePosition(String input) {
    try {
      String[] parts = input.split(",");

      if (parts.length != 2) {
        return null;
      }

      int row = Integer.parseInt(parts[0].trim());
      int col = Integer.parseInt(parts[1].trim());

      if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
        return null;
      }

      return new int[]{row, col};
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static void visualize(int[] pos) {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    board[pos[0]][pos[1]] = 2;

    printBoard(board, true);
    algorithm(board, pos[0], pos[1]);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void printBoard(int[][] board, boolean initialize) {
    // Print the chess board with the given state
    clearConsole();

    for (int[] row : board) {
      for (int cell : row) {
        char c;

        switch (cell) {
          // Visited
          case 1:
            c = 'V';
            break;
          
          // Knight
          case 2:
            c = 'K';
            break;

          // Unvisited
          default:
            c = '.';
            break;
        }

        System.out.println(c + " ");
      }

      System.out.println();
    }

    if (initialize) {
      System.out.println("Initializing...");
    }

    try {
      // Simulate delay
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void algorithm(int[][] board, int krow, int kcol) {
    for (int step = 0; step < BOARD_SIZE * BOARD_SIZE; step++) {
      board[krow][kcol] = 1;
      PriorityQueue<Neighbor> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.count, b.count));

      for (int i = 0; i < DX.length; i++) {
        int nrow = krow + DX[i];
        int ncol = kcol + DY[i];

        if (nrow >= 0 && nrow < BOARD_SIZE && ncol >= 0 && ncol < BOARD_SIZE && board[nrow][ncol] == 0) {
          int count = 0;

          for (int j = 0; j < DX.length; j++) {
            int nnrow = nrow + DX[j];
            int nncol = ncol + DY[j];

            if (nnrow >= 0 && nnrow < BOARD_SIZE && nncol >= 0 && nncol < BOARD_SIZE && board[nnrow][nncol] == 0) {
              count++;
            }
          }

          pq.add(new Neighbor(count, i));
        }
      }

      if (!pq.isEmpty()) {
        Neighbor next = pq.poll();

        krow += DX[next.index];
        kcol += DY[next.index];

        board[krow][kcol] = 2;
        printBoard(board, false);
      } else {
        board[krow][kcol] = 1;
        printBoard(board, false);
        break;
      }
    }
  }

  private static class Neighbor {
    int count;
    int index;

    Neighbor(int count, int index) {
      this.count = count;
      this.index = index;
    }
  }
}
