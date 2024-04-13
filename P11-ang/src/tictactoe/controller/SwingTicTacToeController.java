package tictactoe.controller;

public class SwingTicTacToeControllerTest {

  private TicTacToeModel model;
  private MockTicTacToeView view;
  private SwingTicTacToeController controller;

  @Before
  public void setUp() {
    model = new TicTacToeModel();
    view = new MockTicTacToeView();
    controller = new SwingTicTacToeController(view, model);
  }

  @Test
  public void testHandleMove_ValidMove() {
    controller.handleMove(0, 0);
    assertEquals(Player.O, model.getTurn());
  }

  @Test
  public void testHandleMove_GameOver_WinnerExists() {
    // Simulate X winning the game
    model.move(0, 0);
    model.move(1, 0);
    model.move(0, 1);
    model.move(1, 1);
    model.move(0, 2); // X wins horizontally
    controller.handleMove(2, 2);
    assertEquals(true, model.isGameOver());
    assertEquals(Player.X, model.getWinner());
  }

  @Test
  public void testHandleMove_GameOver_Draw() {
    // Simulate a draw game
    model.move(0, 0);
    model.move(1, 1);
    model.move(0, 1);
    model.move(0, 2);
    model.move(1, 2);
    model.move(1, 0);
    model.move(2, 0);
    model.move(2, 1);
    model.move(2, 2); // Game ends in a draw
    controller.handleMove(2, 2);
    assertEquals(true, model.isGameOver());
    assertEquals(null, model.getWinner());
  }

  @Test
  public void testHandleMove_Exception() {
    // Try to make an invalid move
    controller.handleMove(0, 0);
    controller.handleMove(0, 0);
    assertEquals("Invalid move.", view.getLastMessage());
  }

  @Test
  public void testPlayGame() {
    TicTacToeModel newModel = new TicTacToeModel();
    controller.playGame(newModel);
    assertEquals("Game started. Player X's turn.", view.getLastMessage());
  }

  // Mock TicTacToeView implementation for testing
  private class MockTicTacToeView implements TicTacToeView {
    private String lastMessage;

    @Override
    public void setButtonListener(SwingTicTacToeController controller) {
      // Not needed for testing
    }

    @Override
    public void clearBoard() {
      // Not needed for testing
    }

    @Override
    public void showMessage(String message) {
      lastMessage = message;
    }

    @Override
    public void setButton(int row, int col, Player player) {
      // Not needed for testing
    }

    public String getLastMessage() {
      return lastMessage;
    }
  }
}
