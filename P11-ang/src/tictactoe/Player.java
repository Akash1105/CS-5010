package tictactoe;

/**
 * The enum tictactoe.Player.
 */
public enum Player {
  /**
   * The X.
   */
X {
    @Override
    public String toString() {
      return "X";
    }
  },
  /** The O. */
  O {
    @Override
    public String toString() {
      return "O";
    }
  };
}
