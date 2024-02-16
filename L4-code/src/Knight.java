/**
 * This class represents a Knight that extends AbstractChessPiece.
 */
public class Knight extends AbstractChessPiece {

  /**
   * Constructs a Knight object and initializes it to the given row, column and color.
   *
   * @param row   the row of this Knight
   * @param col   the column of this Knight
   * @param color the color of this Knight
   * @throws IllegalArgumentException if the given row or column is out of range
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Returns true if the Knight can move to the given row and column, otherwise false.
   *
   * @param row the row to move to
   * @param col the column to move to
   * @return true if the Knight can move to the given row and column, otherwise false
   */
  @Override
  public boolean canMove(int row, int col) {
    int deltaRow = Math.abs(row - getRow());
    int deltaCol = Math.abs(col - getColumn());

    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
  }
}
