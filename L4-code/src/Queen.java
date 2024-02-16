/**
 * This class represents a Queen that extends AbstractChessPiece.
 */
public class Queen extends AbstractChessPiece {

  /**
   * Constructs a Queen object and initializes it to the given row, column and color.
   *
   * @param row   the row of this Queen
   * @param col   the column of this Queen
   * @param color the color of this Queen
   * @throws IllegalArgumentException if the given row or column is out of range
   */
  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Returns true if the Queen can move to the given row and column, otherwise false.
   *
   * @param row the row to move to
   * @param col the column to move to
   * @return true if the Queen can move to the given row and column, otherwise false
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return ((this.getRow() == row) || (this.getColumn() == col)
            || (Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col)));
  }
}
