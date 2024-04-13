package tictactoe;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingTicTacToeController implements TicTacToeController {
  private TicTacToe model;
  private TicTacToeView view;

  public SwingTicTacToeController(TicTacToeView view, TicTacToe model) {
    this.model = model;
    this.view = view;
    initView();
  }

  private void initView() {
    // Initialize the game board in the view
    view.initializeGameBoard();
    // Add action listeners to the game board's cells
    int BOARDSIZE = 3;
    for (int row = 0; row < BOARDSIZE; row++) {
      for (int col = 0; col < BOARDSIZE; col++) {
        int finalRow = row;
        int finalCol = col;
        JButton button = view.getButtonAt(row, col);
        button.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
              model.move(finalRow, finalCol);
              view.updateGameBoard();
              if (model.isGameOver()) {
                view.displayGameOutcome(model.getWinner());
              } else {
                view.displayPlayerTurn(model.getTurn());
              }
            }
          }
        });
      }
    }
  }

  @Override
  public void playGame(TicTacToe m) {
    // The game loop is handled by the GUI event listeners.
    throw new UnsupportedOperationException("playGame is not supported. The game is controlled by the GUI.");
  }
}