import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Tic tac toe model.
 */
public class TicTacToeModel implements TicTacToe {

  private Player[][] board;
  private Player turn;
  private boolean gameOver;
  private Player winner;

  /**
   * Instantiates a new Tic tac toe model.
   */
  public TicTacToeModel() {
    // Initialize the board
    board = new Player[3][3];
    // Player X starts the game
    turn = Player.X;
    // Game starts with no winner and not over
    gameOver = false;
    winner = null;
  }

  private boolean isBoardFull() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (board[row][col] == null) {
          return false;
        }
      }
    }
    return true;
  }

  // Helper method to check for a win
  private boolean checkWin(Player player) {
    // Check rows
    for (int row = 0; row < 3; row++) {
      if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
        return true;
      }
    }
    // Check columns
    for (int col = 0; col < 3; col++) {
      if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
        return true;
      }
    }
    // Check diagonals
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return true;
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return true;
    }
    return false;
  }

  // Helper method to copy the board
  private Player[][] copyBoard(Player[][] board) {
    Player[][] copy = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, 3);
    }
    return copy;
  }

  /**
   * Move.
   *
   * @param r the r
   * @param c the c
   */
  @Override
  public void move(int r, int c) {
    // Check if the game is over
    if (gameOver)
      throw new IllegalStateException("The game is already over.");

    // Check if the position is valid
    if (r < 0 || r >= 3 || c < 0 || c >= 3 || board[r][c] != null)
      throw new IllegalArgumentException("Invalid move.");

    // Set the mark for the current player
    board[r][c] = turn;

    // Check if the game is over after this move
    if (checkWin(turn) || isBoardFull()) {
      gameOver = true;
      if (checkWin(turn))
        winner = turn;
    } else {
      // Switch turn to the other player
      turn = (turn == Player.X) ? Player.O : Player.X;
    }

  }

  /**
   * Gets turn.
   *
   * @return the turn
   */
  @Override
  public Player getTurn() {
    return turn;
  }

  /**
   * Is game over boolean.
   *
   * @return the boolean
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Gets winner.
   *
   * @return the winner
   */
  @Override
  public Player getWinner() {
    return winner;
  }

  /**
   * Get board player [ ] [ ].
   *
   * @return the player [ ] [ ]
   */
  @Override
  public Player[][] getBoard() {
    return copyBoard(board);
  }

  /**
   * Gets mark at.
   *
   * @param r the r
   * @param c the c
   * @return the mark at
   * @throws IllegalArgumentException the illegal argument exception
   */
  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= 3 || c < 0 || c >= 3)
      throw new IllegalArgumentException("Invalid position.");
    return board[r][c];
  }

  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
     //This is the equivalent code as above, but using iteration, and still using
     //the helpful built-in String.join method.
    /**********
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
    ************/
  }
}
