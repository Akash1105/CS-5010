package tictactoe.model;

import tictactoe.Player;

public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player turn;
  private boolean gameOver;
  private Player winner;

  public TicTacToeModel() {
    board = new Player[3][3];
    turn = Player.X;
    gameOver = false;
    winner = null;
  }

  @Override
  public void move(int r, int c) {
    if (gameOver) {
      throw new IllegalStateException("The game is already over.");
    }
    if (r < 0 || r >= 3 || c < 0 || c >= 3 || board[r][c] != null) {
      throw new IllegalArgumentException("Invalid move.");
    }

    board[r][c] = turn;
    checkWin();
    checkDraw();
    turn = (turn == Player.X) ? Player.O : Player.X;
  }

  private void checkWin() {
    // Check rows
    for (int i = 0; i < 3; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != null) {
        gameOver = true;
        winner = board[i][0];
        return;
      }
    }
    // Check columns
    for (int i = 0; i < 3; i++) {
      if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != null) {
        gameOver = true;
        winner = board[0][i];
        return;
      }
    }
    // Check diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != null) {
      gameOver = true;
      winner = board[0][0];
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != null) {
      gameOver = true;
      winner = board[0][2];
    }
  }

  private void checkDraw() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == null) {
          return; // Game is not a draw yet
        }
      }
    }
    // All cells are filled, but no winner => draw
    gameOver = true;
  }

  @Override
  public Player getTurn() {
    return turn;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] copy = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, 3);
    }
    return copy;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return board[r][c];
  }
}
