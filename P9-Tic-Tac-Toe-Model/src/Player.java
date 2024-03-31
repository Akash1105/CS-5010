/**
 * The enum Player.
 */
public enum Player {
  /**
   * The Player X.
   */
  X {
    @Override
    public String toString() {
      return "X";
    }
  },
  /**
   * The Player O.
   */
  O {
    @Override
    public String toString() {
      return "O";
    }
  }
}
