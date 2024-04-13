package tictactoe.view;

import tictactoe.Player;
import tictactoe.controller.SwingTicTacToeController;

public interface TicTacToeView {
  void setButtonListener(SwingTicTacToeController controller);
  void clearBoard();
  void showMessage(String message);
  void setButton(int row, int col, Player player);
}
