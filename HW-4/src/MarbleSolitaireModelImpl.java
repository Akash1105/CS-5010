

/**
 * The type Marble solitaire model.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private final int EMPTY = 0;
  private final int MARBLE = 1;
  private final int INVALID = 2;

  private final int[][] board;
  private final int size;
  private int score;

  /**
   * Instantiates a new Marble solitaire model.
   */
  public MarbleSolitaireModelImpl() {
    this(3);
  }

  /**
   * Instantiates a new Marble solitaire model.
   *
   * @param armThickness the arm thickness
   * @throws IllegalArgumentException the illegal argument exception
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    this(armThickness, (armThickness * 3 - 2) / 2, (armThickness * 3 - 2) / 2);
  }

  /**
   * Instantiates a new Marble solitaire model.
   *
   * @param armThickness the arm thickness
   * @param emptyRow     the empty row
   * @param emptyColumn  the empty column
   * @throws IllegalArgumentException the illegal argument exception
   */
  public MarbleSolitaireModelImpl(int armThickness, int emptyRow, int emptyColumn)
      throws IllegalArgumentException {
    validateArmThickness(armThickness);
    this.size = 3 * armThickness - 2;
    this.score = 4 * armThickness * armThickness - 4;
    this.board = new int[size][size];
    initializeBoard(armThickness);
    validateEmptyCell(emptyRow, emptyColumn);
    board[emptyRow][emptyColumn] = EMPTY;
  }

  private void validateArmThickness(int armThickness) {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number.");
    }
  }

  private void initializeBoard(int armThickness) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((i < armThickness - 1 || size - i <= armThickness - 1) && (j < armThickness - 1
            || size - j <= armThickness - 1)) {
          this.board[i][j] = INVALID;
        } else {
          this.board[i][j] = MARBLE;
        }
      }
    }
  }

  private void validateEmptyCell(int emptyRow, int emptyColumn) {
    if (emptyRow < 0 || emptyRow >= size || emptyColumn < 0 || emptyColumn >= size
        || board[emptyRow][emptyColumn] != MARBLE) {
      throw new IllegalArgumentException(
          String.format("Invalid empty cell position (%d, %d)", emptyRow, emptyColumn));
    }
  }

  /**
   * Move.
   *
   * @param fromRow the from row
   * @param fromCol the from col
   * @param toRow   the to row
   * @param toCol   the to col
   * @throws IllegalArgumentException the illegal argument exception
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move");
    }
    board[(toRow + fromRow) / 2][(toCol + fromCol) / 2] = EMPTY;
    board[toRow][toCol] = MARBLE;
    board[fromRow][fromCol] = EMPTY;
    score--;
  }

  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return isInBounds(fromRow, fromCol, toRow, toCol) &&
        board[fromRow][fromCol] == MARBLE &&
        board[toRow][toCol] == EMPTY &&
        isTwoSpacesAway(fromRow, fromCol, toRow, toCol) &&
        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == MARBLE;
  }

  private boolean isInBounds(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow >= 0 && fromRow < size && fromCol >= 0 && fromCol < size &&
        toRow >= 0 && toRow < size && toCol >= 0 && toCol < size;
  }

  private boolean isTwoSpacesAway(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(toRow - fromRow) == 2 && toCol == fromCol
        || Math.abs(toCol - fromCol) == 2 && toRow == fromRow;
  }

  /**
   * Is game over boolean.
   *
   * @return the boolean
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (board[row][col] == MARBLE) {
          if (canMove(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private boolean canMove(int row, int col) {
    return isValidMove(row, col, row + 2, col) ||
        isValidMove(row, col, row - 2, col) ||
        isValidMove(row, col, row, col + 2) ||
        isValidMove(row, col, row, col - 2);
  }

  /**
   * Gets game state.
   *
   * @return the game state
   */
  @Override
  public String getGameState() {
    StringBuilder msg = new StringBuilder(2 * size * size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board[i][j] == MARBLE) {
          msg.append("O ");
        } else if (board[i][j] == EMPTY) {
          msg.append("_ ");
        } else {
          msg.append("  ");
        }
      }
      msg.deleteCharAt(msg.length() - 1);
      msg.append("\n");
    }
    msg.deleteCharAt(msg.length() - 1);
    return msg.toString();
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  @Override
  public int getScore() {
    return score;
  }
}
