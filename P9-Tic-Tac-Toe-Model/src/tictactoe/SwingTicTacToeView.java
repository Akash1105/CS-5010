package tictactoe;

import javax.swing.*;
import java.awt.*;

public class SwingTicTacToeView implements TicTacToeView {
  private JFrame frame;
  private JButton[][] buttons = new JButton[3][3];
  private JLabel statusLabel;
  private String title;

  public SwingTicTacToeView(String t) {
    title = t;
    initializeUI();
  }

  @Override
  public void initializeGameBoard() {
    frame.setVisible(true);
  }

  private void initializeUI() {
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLayout(new BorderLayout());

    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(3, 3));
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        JButton button = new JButton();
        buttons[row][col] = button;
        boardPanel.add(button);
      }
    }

    statusLabel = new JLabel("Turn: X");
    frame.add(statusLabel, BorderLayout.SOUTH);
    frame.add(boardPanel, BorderLayout.CENTER);
  }

  @Override
  public void updateGameBoard() {
    // This method will be called to update the board.
    // It should iterate through the buttons array and update each button's text according to the TicTacToeModel.
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        buttons[i][j].setText(buttons[i][j] == null ? "" : buttons[i][j].toString());
      }
    }
  }

  @Override
  public void displayPlayerTurn(Player player) {
    statusLabel.setText("Turn: " + player);
  }

  @Override
  public void displayGameOutcome(Player winner) {
    String message = (winner == null) ? "Game is a draw!" : "Winner is " + winner + "!";
    JOptionPane.showMessageDialog(frame, message);
  }

  @Override
  public JButton getButtonAt(int row, int col) {
    return buttons[row][col];
  }
}