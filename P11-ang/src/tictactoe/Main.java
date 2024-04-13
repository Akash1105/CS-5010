package tictactoe;

import tictactoe.controller.*;
import tictactoe.model.TicTacToe;
import tictactoe.model.TicTacToeModel;
import tictactoe.view.SwingTicTacToeView;
import tictactoe.view.TicTacToeView;

/**
 * Run a tictactoe.model.TicTacToe game interactively on the console.
 */
public class Main {
  /**
   * Run a tictactoe.model.TicTacToe game interactively on the console.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    TicTacToe m = new TicTacToeModel();
    TicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe");
    TicTacToeController c = new SwingTicTacToeController(v, m);
    c.playGame(m);
  }
}
