/** Bishop class that extends AbstractChessPiece. */
public class Bishop extends AbstractChessPiece {

  /**
   * Constructs a Bishop object and initializes it to the given row, column and color.
   *
   * @param row   the row of this Bishop
   * @param col   the column of this Bishop
   * @param color the color of this Bishop
   * @throws IllegalArgumentException if the given row or column is out of range
   */
  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Returns true if the Bishop can move to the given row and column, otherwise false.
   *
   * @param row the row to move to
   * @param col the column to move to
   * @return true if the Bishop can move to the given row and column, otherwise false
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return (Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col));
  }
}
