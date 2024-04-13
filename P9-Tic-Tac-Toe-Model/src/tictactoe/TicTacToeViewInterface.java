package tictactoe;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import tictactoe.Player;

public interface TicTacToeViewInterface {

  // Update the game board displayed in the view
  void updateBoard(Player[][] board);

  // Update the status message displayed in the view
  void updateStatus(String status);

  // Set an action listener on the buttons in the view
  void setButtonListener(ActionListener listener);

  // Example method to get a specific button from the view (if applicable)
  JButton getButton(int row, int col);
}
