import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {

  private boolean[][] results;

  /**
   * Set up method to initialize variables before each test.
   */
  @Before
  public void setup() {
    results = new boolean[8][8];
  }

  /**
   * Verifies the results of canMove for the given ChessPiece.
   */
  private void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }

        assertEquals("Piece at: " + piece.getRow() + "," + piece.getColumn() +
                ", Unexpected canMove result for i=" + i + " j=" + j + "",
            results[i][j], piece.canMove(i, j));
      }
    }
  }

  /**
   * Verifies the results of canKill for the given ChessPiece.
   */
  private void verifyKillResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        ChessPiece another = new Knight(i, j,
            Color.values()[(piece.getColor().ordinal() + 1)
                % Color.values().length]);

        assertEquals("Unexpected canKill result for i=" + i + " j=" + j + "",
            results[i][j], piece.canKill(another));
      }
    }
  }

  /**
   * Tests the getters of the Knight class.
   */
  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = new Knight(row, col, c);
          assertEquals("Row number does not match what was initialized", row,
              piece.getRow());
          assertEquals("Column number does not match what was initialized",
              col, piece.getColumn());
          assertEquals("Color does not match what was initialized",
              c, piece.getColor());
        }
      }
    }
  }

  /**
   * Tests invalid constructions of the Knight class.
   */
  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {
        try {
          piece = new Knight(i, -1, c);
          fail("Did not throw an exception when knight is created with invalid column");
        } catch (IllegalArgumentException e) {
          // passes
        }

        try {
          piece = new Knight(-1, i, c);
          fail("Did not throw an exception when knight is created with invalid row");
        } catch (IllegalArgumentException e) {
          // passes
        }
      }
    }
  }

  /**
   * Tests the move functionality of the Knight class.
   */
  @Test(timeout = 500)
  public void testKnightMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Knight(row, col, Color.BLACK);

        setupResults(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  /**
   * Tests the kill functionality of the Knight class.
   */
  @Test(timeout = 500)
  public void testKnightKills() {
    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Knight(row, col, c);

          setupResults(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }

  /**
   * Initializes the results array to false.
   */
  private void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  /**
   * Sets up the expected results for the move and kill tests.
   *
   * @param row The row of the piece.
   * @param col The column of the piece.
   */
  private void setupResults(int row, int col) {
    // check if canMove works
    for (int i = -2; i <= 2; i++) {
      for (int j = -2; j <= 2; j++) {
        if (Math.abs(i) + Math.abs(j) == 3 && row + i >= 0 && row + i < 8 && col + j >= 0
            && col + j < 8) {
          results[row + i][col + j] = true;
        }
      }
    }
  }
}
