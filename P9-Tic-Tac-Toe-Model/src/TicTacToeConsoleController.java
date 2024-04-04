import java.io.IOException;
import java.util.Scanner;

/**
 * The type Tic tac toe console controller.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Instantiates a new Tic tac toe console controller.
   *
   * @param in  the in
   * @param out the out
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.scan = new Scanner(in);
    this.out = out;
  }

  /**
   * Play game.
   *
   * @param m the m
   */
  @Override
  public void playGame(TicTacToe m) {
    boolean isLastMoveValid = true;
    try {
      while (!m.isGameOver()) {
        if (isLastMoveValid) {
          out.append(m.toString()).append("\n");
          out.append("Enter a move for player ").append(m.getTurn().toString()).append(":\n");
        }
        String row = null, col = null;
        try {
          row = scan.next();
          if (quitGameCheck(row, m)) {
            return;
          }
          col = scan.next();
          if (quitGameCheck(col, m)) {
            return;
          }
          m.move(Integer.parseInt(row) - 0, Integer.parseInt(col) - 0);
          isLastMoveValid = true;
        } catch (NumberFormatException e) {
          isLastMoveValid = false;
          out.append("Not a valid number! Make sure to enter a digit : ")
              .append(e.getMessage().split("\"")[1]).append("\n");
        } catch (IllegalArgumentException e) {
          isLastMoveValid = false;
          out.append("Not a valid move: ").append(row).append(", ").append(col).append("\n");
        } catch (IllegalStateException e) {
          out.append(e.getMessage()).append("\n");
        }
      }
      out.append(m.toString()).append("\n");
      if (m.getWinner() != null) {
        out.append("Game is over! ").append(m.getWinner().toString()).append(" wins the game.");
      } else {
        out.append("Game is over! Game tied.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }

  /**
   * Quit game check boolean.
   *
   * @param s the s
   * @param m the m
   * @return the boolean
   * @throws IOException the io exception
   */
  private boolean quitGameCheck(String s, TicTacToe m) throws IOException {
    if (s.equalsIgnoreCase("q")) {
      out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
      return true;
    }
    return false;
  }

}
