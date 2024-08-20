import java.io.IOException;

public class ConsoleUtils {
  public static void clearConsole() {
    // Clears the console
    String os = System.getProperty("os.name").toLowerCase();

    try {
      if (os.contains("win")) {
        // For Windows
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        // For Mac and Linux
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void printDummyBoard() {
    // Print dummy board with indexes for all the cells
    int j = 0;

    for (int i = 0; i < 9; i++) {
      if (i != 0) {
        System.out.println(j + "   " + "\u001B[33m|   |   |   |   |   |   |   |   |");
        System.out.println("    " + "\u001B[33m---------------------------------");
        j++;
      } else {
        System.out.println("      0   1   2   3   4   5   6   7");
        System.out.println("    " + "\u001B[33m---------------------------------");
      }
    }

    System.out.println();
  }

  public static boolean validate(String pos) {
    // Check if user's input is valid
    try {
      String[] parts = pos.split(",");

      if (parts.length != 2) {
        return false;
      }

      int row = Integer.parseInt(parts[0].trim());
      int col = Integer.parseInt(parts[1].trim());

      if (row < 0 || row > 7 || col < 0 || col > 7) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }

    return false;
  }
}
