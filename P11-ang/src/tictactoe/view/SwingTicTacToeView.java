package tictactoe.view;

import javax.swing.*;
import java.awt.*;
import tictactoe.Player;
import tictactoe.controller.SwingTicTacToeController;

public class SwingTicTacToeView implements TicTacToeView {
  private JButton[][] buttons;
  private SwingTicTacToeController controller;

  public SwingTicTacToeView(String title) {
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(3, 3));

    buttons = new JButton[3][3];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        button.addActionListener(e -> {
          JButton clickedButton = (JButton) e.getSource();
          int buttonRow = -1;
          int buttonCol = -1;
          // Find the clicked button's position
          for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
              if (buttons[r][c] == clickedButton) {
                buttonRow = r;
                buttonCol = c;
                break;
              }
            }
          }
          // Call controller method when a button is clicked
          controller.handleMove(buttonRow, buttonCol);
        });
        buttons[row][col] = button;
        frame.add(button);
      }
    }

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  public void setButtonListener(SwingTicTacToeController controller) {
    this.controller = controller;
  }

  @Override
  public void clearBoard() {
    for (JButton[] buttonRow : buttons) {
      for (JButton button : buttonRow) {
        button.setText("");
        button.setEnabled(true);
      }
    }
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  @Override
  public void setButton(int row, int col, Player player) {
    buttons[row][col].setText(player.toString());
    buttons[row][col].setEnabled(false);
  }
}
