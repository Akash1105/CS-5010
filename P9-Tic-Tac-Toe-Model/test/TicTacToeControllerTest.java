import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  private TicTacToeModel model;
  private StringBuilder log;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    model = new TicTacToeModel();
    log = new StringBuilder();
  }
  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner
  // Input where the q comes instead of an integer for the row
  // Input where the q comes instead of an integer for the column
  // Input where non-integer garbage comes instead of an integer for the row
  // Input where non-integer garbage comes instead of an integer for the column
  // Input where the move is integers, but outside the bounds of the board
  // Input where the move is integers, but invalid because the cell is occupied
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input, but not quit, and game not over
  // THIS IS NOT AN EXHAUSTIVE LIST

  /**
   * Test game completion with winner.
   */
  @Test
  public void testGameCompletionWithWinner() {
    StringReader input = new StringReader("1 1\n1 2\n2 2\n0 2\n0 0\nq\n");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    System.out.print(log);
    assertTrue(log.toString().contains("Game is over! X wins the game."));
  }

  /**
   * Test quit instead of row.
   */
  @Test
  public void testQuitInsteadOfRow() {
    StringReader input = new StringReader("q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals(12, lines.length);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
  }

  /**
   * Test quit instead of col.
   */
  @Test
  public void testQuitInsteadOfCol() {
    StringReader input = new StringReader("1 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals(12, lines.length);
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
  }

  /**
   * Test non integer input for row.
   */
  @Test
  public void testNonIntegerInputForRow() {
    StringReader input = new StringReader("two 2\nq\n");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid number! Make sure to enter a digit : two", lines[6]);
  }

  /**
   * Test non integer input for col.
   */
  @Test
  public void testNonIntegerInputForCol() {
    StringReader input = new StringReader("2 two\nq\n");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid number! Make sure to enter a digit : two", lines[6]);
  }

  /**
   * Test move out of bounds.
   */
  @Test
  public void testMoveOutOfBounds() {
    StringReader input = new StringReader("4 4 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid move: 4, 4", lines[6]);
  }

  /**
   * Test move to occupied cell.
   */
  @Test
  public void testMoveToOccupiedCell() {
    StringReader input = new StringReader("1 1 1 1 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid move: 1, 1", lines[12]);
  }

  /**
   * Test multiple invalid moves.
   */
  @Test
  public void testMultipleInvalidMoves() {
    StringReader input = new StringReader("1 1 0 zero 1 1 one 1 1 one q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid number! Make sure to enter a digit : zero", lines[12]);
    assertEquals("Not a valid move: 1, 1", lines[13]);
    assertEquals("Not a valid number! Make sure to enter a digit : one", lines[14]);
    assertEquals("Not a valid number! Make sure to enter a digit : one", lines[15]);
  }

  /**
   * Test valid moves interspersed with invalid moves.
   */
  @Test
  public void testValidMovesInterspersedWithInvalidMoves() {
    StringReader input = new StringReader("1 1 0 0 1 2 1 1 2 1 one 1 1 one 1 3 1 0 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for player X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for player O:\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for player X:\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X | X\n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for player O:\n"
        + "Not a valid move: 1, 1\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X | X\n"
        + "-----------\n"
        + "   | O |  \n"
        + "Enter a move for player X:\n"
        + "Not a valid number! Make sure to enter a digit : one\n"
        + "Not a valid number! Make sure to enter a digit : one\n"
        + "Not a valid move: 1, 3\n"
        + " O |   |  \n"
        + "-----------\n"
        + " X | X | X\n"
        + "-----------\n"
        + "   | O |  \n"
        + "Game is over! X wins the game.", log.toString());
  }

  /**
   * Test input ends abruptly.
   */
  @Test(expected = IllegalStateException.class)
  public void testInputEndsAbruptly() {
    StringReader input = new StringReader(
        "1 1 1 2"); // No more input after valid moves, no quit command
    Appendable failingLog = new FailingAppendable();
    TicTacToeController controller = new TicTacToeConsoleController(input, failingLog);
    controller.playGame(model);
  }


  /**
   * Test single valid move.
   */
  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for player X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   | X\n"
        + "Enter a move for player O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   | X\n", gameLog.toString());
  }

  /**
   * Test bogus input as row.
   */
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test tie game.
   */
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    //StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2 q");
    StringReader input = new StringReader("1 1 0 0 2 2 0 1 0 2 1 2 1 0 2 0 2 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Game tied.", lines[lines.length - 1]);
  }

  /**
   * Test failing appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

}
