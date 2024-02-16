/**
 * This class represents an abstract chess piece. It implements the ChessPiece interface and
 * provides the basic functionality of a chess piece. It has a row, a column, and a color. It
 * provides the basic functionality of a chess piece, such as getting the row, column, and color of
 * the piece, and checking if the piece can kill another piece.
 */
public abstract class AbstractChessPiece implements ChessPiece {

  private int row;
  private int col;
  private Color color;

  /**
   * Constructs an abstract chess piece with the given row, column, and color.
   *
   * @param row the row of the piece
   * @param col the column of the piece
   * @param color the color of the piece
   * @throws IllegalArgumentException if the row or column is negative
   */
  public AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  /**
   * Get the row of the current position of this piece. Rows begin with 0.
   *
   * @return the row of the current position of this piece
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Get the column of the current position of this piece. Columns begin with 0.
   *
   * @return the column of the current position of this piece
   */
  @Override
  public int getColumn() {
    return col;
  }

  /**
   * Get the column of the current position of this piece Columns begin with 0.
   *
   * @return the column of the current position of this piece
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Get the color of this piece. The color can be one of WHITE or BLACK.
   *
   * @return the color of this chess piece
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(
        piece.getRow(),
        piece.getColumn());
  }

}
