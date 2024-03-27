
/**
 * The type Main.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl();

    // Print initial game state
    System.out.println("Initial Game State:");
    System.out.println(model.getGameState());

    // Make some moves
    System.out.println("\nMaking some moves...");

    while (!model.isGameOver()) {
      makeMove(model);
      System.out.println("Game State after Move:");
      System.out.println(model.getGameState());
    }

    // Print the final state and score
    System.out.println("\nGame Over!");
    System.out.println("Final Score: " + model.getScore());

    // Print updated game state
    System.out.println("\nUpdated Game State:");
    System.out.println(model.getGameState());

    // Check if game is over
    if (model.isGameOver()) {
      System.out.println("\nGame Over!");
      System.out.println("Final Score: " + model.getScore());
    } else {
      System.out.println("\nGame is not over yet.");
      System.out.println("Current Score: " + model.getScore());
    }
  }

  private static void makeMove(MarbleSolitaireModelImpl model) {
    // Iterate through each cell on the board and try to make a valid move
    int size = 3 * 3 - 2;
    for (int fromRow = 0; fromRow < size; fromRow++) {
      for (int fromCol = 0; fromCol < size; fromCol++) {
        for (int toRow = 0; toRow < size; toRow++) {
          for (int toCol = 0; toCol < size; toCol++) {
            try {
              // Attempt to make a move
              model.move(fromRow, fromCol, toRow, toCol);
              return; // If move is successful, exit the method
            } catch (IllegalArgumentException e) {
              // Move is invalid, continue iterating
            }
          }
        }
      }
    }

  }
}
