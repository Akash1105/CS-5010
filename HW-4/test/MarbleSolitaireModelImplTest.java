import org.junit.Test;
import static org.junit.Assert.*;
/**
 * The type Marble solitaire model impl test.
 */
public class MarbleSolitaireModelImplTest {

  /**
   * Test initialization.
   */
  @Test
  public void testInitialization() {
    // Expected game state for arm thickness of 3
    String expected = "    O O O    \n" +
        "    O O O    \n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O    \n" +
        "    O O O    ";

    MarbleSolitaireModelImpl game = new MarbleSolitaireModelImpl(3);
    String actual = game.getGameState();
    System.out.println(actual);
    assertEquals(expected, actual);
  }

  /**
   * Test valid move.
   */
  @Test
  public void testValidMove() {
    MarbleSolitaireModelImpl game = new MarbleSolitaireModelImpl();
    game.move(1, 3, 3, 3);
    assertEquals("    O O O    \n" +
        "    O _ O    \n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O    \n" +
        "    O O O    ", game.getGameState());
  }

  /**
   * Test invalid move.
   */
  @Test
  public void testInvalidMove() {
    MarbleSolitaireModelImpl game = new MarbleSolitaireModelImpl(3);
    assertThrows(IllegalArgumentException.class, () -> game.move(2, 2, 2, 4));
  }

  /**
   * Test game over.
   */
  @Test
  public void testGameOver() {
    MarbleSolitaireModelImpl game = new MarbleSolitaireModelImpl(3);
    game.move(1, 3, 3, 3);
    assertFalse(game.isGameOver());
    game.move(4, 3, 2, 3);
    assertFalse(game.isGameOver());
    game.move(3, 1, 3, 3);
    assertFalse(game.isGameOver());
    game.move(3, 4, 3, 2);
    assertFalse(game.isGameOver());
    game.move(3, 6, 3, 4);
    assertFalse(game.isGameOver());
    game.move(6, 3, 4, 3);
    assertTrue(game.isGameOver());
  }

  /**
   * Test constructor invalid arm thickness even.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_invalidArmThickness_even() {
    new MarbleSolitaireModelImpl(4); // Even arm thickness
  }

  /**
   * Test constructor invalid arm thickness zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_invalidArmThickness_zero() {
    new MarbleSolitaireModelImpl(0); // Zero arm thickness
  }

  /**
   * Test constructor invalid empty cell out of bounds row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_invalidEmptyCell_outOfBoundsRow() {
    new MarbleSolitaireModelImpl(3, -1, 1); // Row out of bounds
  }


  /**
   * Test constructor invalid empty cell not marble.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_invalidEmptyCell_notMarble() {
    new MarbleSolitaireModelImpl(3, 1, 1); // Empty cell not a marble
  }


  /**
   * Test constructor custom arm thickness.
   */
  @Test
  public void testConstructor_customArmThickness() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(5);
    int expectedScore = 4 * 5 * 5 - 4; // Calculate expected score based on arm thickness
    assertEquals(expectedScore, model.getScore());  // Score reflects arm thickness
  }


  /**
   * Test move invalid move out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove_invalidMove_outOfBounds() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(3);
    model.move(0, 0, 4, 0); // Out of bounds move
  }

  /**
   * Test move invalid move not two spaces away.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove_invalidMove_notTwoSpacesAway() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(3);
    model.move(1, 1, 2, 1); // Not two spaces away
  }

  /**
   * Test move invalid move no marble in between.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove_invalidMove_noMarbleInBetween() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(3);
    model.move(1, 0, 3, 0); // No marble in between
  }


  /**
   * Test is game over not game over.
   */
  @Test
  public void testIsGameOver_notGameOver() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(3);
    assertFalse(model.isGameOver()); // Default state has valid moves
  }

  /**
   * Test get score.
   */
  @Test
  public void testGetScore() {
    MarbleSolitaireModelImpl model = new MarbleSolitaireModelImpl(3);
    assertEquals(32, model.getScore());
  }
}
