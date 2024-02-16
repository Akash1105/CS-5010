/**
 * This class represents a Rook that extends AbstractChessPiece.
 */
public class Rook extends AbstractChessPiece {

  /**
   * Constructs a Rook object and initializes it to the given row, column and color.
   *
   * @param row   the row of this Rook
   * @param col   the column of this Rook
   * @param color the color of this Rook
   * @throws IllegalArgumentException if the given row or column is out of range
   */
  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Returns true if the Rook can move to the given row and column, otherwise false.
   *
   * @param row the row to move to
   * @param col the column to move to
   * @return true if the Rook can move to the given row and column, otherwise false
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return ((this.getRow() == row) || (this.getColumn() == col));
  }
}
