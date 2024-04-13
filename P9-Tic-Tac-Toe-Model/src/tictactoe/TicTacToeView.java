package tictactoe;

import javax.swing.*;

/**
 * Interface for the view component in a Tic Tac Toe game using Swing.
 */
public interface TicTacToeView {
  /**
   * Initializes the game board and makes it visible.
   */
  void initializeGameBoard();

  /**
   * Updates the game board based on the current state of the model.
   */
  void updateGameBoard();

  /**
   * Displays the current player's turn on the GUI.
   * @param player The current player.
   */
  void displayPlayerTurn(Player player);

  /**
   * Shows the outcome of the game (win/draw) and which player won, if applicable.
   * @param winner The winning player, or null if the game is a draw.
   */
  void displayGameOutcome(Player winner);

  JButton getButtonAt(int row, int col);
}