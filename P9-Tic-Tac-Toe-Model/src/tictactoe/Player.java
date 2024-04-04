package tictactoe;

/**
 * The enum tictactoe.Player.
 */
public enum Player {
  /**
   * The tictactoe.Player X.
   */
  X {
    @Override
    public String toString() {
      return "X";
    }
  },
  /**
   * The tictactoe.Player O.
   */
  O {
    @Override
    public String toString() {
      return "O";
    }
  }
}
