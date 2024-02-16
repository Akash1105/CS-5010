import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The SimplePoolSimulatorTest class contains unit tests for the SimplePoolSimulator class.
 */
public class SimplePoolSimulatorTest {

  /**
   * Tests valid initialization with the "Simple" physics type.
   */
  @Test
  void validInitializationWithSimple() {
    assertDoesNotThrow(() -> new SimplePoolSimulator(800, 600, "Simple"));
  }

  /**
   * Tests valid initialization with the "Friction" physics type.
   */
  @Test
  void validInitializationWithFriction() {
    assertDoesNotThrow(() -> new SimplePoolSimulator(800, 600, "Friction"));
  }

  /**
   * Tests initialization with a negative table width.
   */
  @Test
  void withNegativeWidth() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SimplePoolSimulator(-800, 600, "Simple"));
    assertEquals("Table Width Is Invalid", exception.getMessage());
  }

  /**
   * Tests initialization with a negative table height.
   */
  @Test
  void withNegativeHeight() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SimplePoolSimulator(800, -600, "Simple"));
    assertEquals("Table Height Is Invalid", exception.getMessage());
  }

  /**
   * Tests initialization with an invalid physics type.
   */
  @Test
  void withInvalidType() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SimplePoolSimulator(800, 600, "InvalidType"));
    assertEquals("Physics Type Is Invalid", exception.getMessage());
  }

  /**
   * Tests valid start parameters for the "Simple" physics type.
   */
  @Test
  void validStartParameters() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    assertDoesNotThrow(() -> simulator.start(100, 200, 10, 5, 0.6, 0.8));
  }

  /**
   * Tests invalid start parameters for the "Simple" physics type.
   */
  @Test
  void invalidStartParameters() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(100, 100, "Simple");
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> simulator.start(90, 90, 20, 5, 0.6, 0.8));
    assertEquals("Y Is Invalid.", exception.getMessage());
  }

  /**
   * Tests valid advance for the "Simple" physics type.
   */
  @Test
  void validAdvance_SimpleType() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(100, 200, 10, 5, 0.6, 0.8);
    simulator.advance();
  }

  /**
   * Tests valid advance for the "Friction" physics type.
   */
  @Test
  void validAdvance_FrictionType() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Friction");
    simulator.start(100, 200, 10, 5, 0.6, 0.8);
    simulator.advance();
  }

  /**
   * Tests retrieving the table width.
   */
  @Test
  void getTableWidth() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    assertEquals(800, simulator.getTableWidth());
  }

  /**
   * Tests retrieving the table height.
   */
  @Test
  void getTableHeight() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    assertEquals(600, simulator.getTableHeight());
  }

  /**
   * Tests retrieving the ball radius.
   */
  @Test
  void getBallRadius() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    assertEquals((int) Double.NEGATIVE_INFINITY,
        simulator.getBallRadius()); // Ball not set up initially
    simulator.start(400, 300, 10, 5, 0.6, 0.8);
    assertEquals(10, simulator.getBallRadius());
  }

  /**
   * Tests retrieving the ball velocity X after start.
   */
  @Test
  void getBallVelocityXAfterStart() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(400, 300, 10, 5, 0.6, 0.8);
    assertEquals(3, simulator.getBallVelocityX());
  }

  /**
   * Tests retrieving the ball velocity Y after start.
   */
  @Test
  void getBallVelocityYAfterStart() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(400, 300, 10, 5, 0.6, 0.8);
    assertEquals(4, simulator.getBallVelocityY());
  }

  /**
   * Tests retrieving the status before start.
   */
  @Test
  void getStatusBeforeStart() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    assertEquals("Status: Ball not set up", simulator.getStatus());
  }

  /**
   * Tests retrieving the status after start.
   */
  @Test
  void getStatusAfterStart() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(400, 300, 10, 5, 0.6, 0.8);
    assertEquals("Status: Simulation started", simulator.getStatus());
  }

  /**
   * Tests advancing with a stationary ball.
   */
  @Test
  void advanceWithStationaryBall() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(100, 200, 10, 0, 0.6, 0.8);
    simulator.advance();
    assertEquals("Status: Ball is stationary", simulator.getStatus());
  }

  /**
   * Tests advancing with the ball hitting the right edge.
   */
  @Test
  void advanceBallHitsRightEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(400, 10, 10, 500, 6, 0.6);
    simulator.advance();
    assertEquals("Status: Ball hit right edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the ball hitting the left edge.
   */
  @Test
  void advanceBallHitsLeftEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(10, 300, 10, 50, -0.6, 0.8);
    simulator.advance();
    assertEquals("Status: Ball hit left edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the ball hitting the top edge.
   */
  @Test
  void advanceBallHitsTopEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(790, 300, 10, 500, 6, 0.6);
    simulator.advance();
    assertEquals("Status: Ball hit top edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the ball hitting the bottom edge.
   */
  @Test
  void advanceBallHitsBottomEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(100, 100, 20, 60, 1.1, -2);
    simulator.advance();
    assertEquals("Status: Ball hit bottom edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the ball becoming stationary after hitting an edge.
   */
  @Test
  void advanceBallStationaryAfterHit() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Simple");
    simulator.start(700, 200, 10, 5, -0.6, 0.8);
    simulator.advance();
    assertEquals("Status: Ball is stationary", simulator.getStatus());
  }

  /**
   * Tests advancing with the "Friction" physics type and the ball hitting the right edge.
   */
  @Test
  void WithFrictionBallHitRightEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Friction");
    simulator.start(400, 10, 10, 500, 6, 0.6);
    simulator.advance();
    assertEquals("Status: Ball hit right edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the "Friction" physics type and the ball hitting the left edge.
   */
  @Test
  void WithFrictionBallHitLeftEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Friction");
    simulator.start(100, 300, 20, 50, -1.6, 0.8);
    simulator.advance();
    assertEquals("Status: Ball hit left edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the "Friction" physics type and the ball hitting the top edge.
   */
  @Test
  void WithFrictionBallHitTopEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Friction");
    simulator.start(790, 300, 10, 500, 6, 0.6);
    simulator.advance();
    assertEquals("Status: Ball hit top edge", simulator.getStatus());
  }

  /**
   * Tests advancing with the "Friction" physics type and the ball hitting the bottom edge.
   */
  @Test
  void WithFrictionBallHitBottomEdge() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(800, 600, "Friction");
    simulator.start(100, 100, 20, 60, 0.8, -2);
    simulator.advance();
    assertEquals("Status: Ball hit bottom edge", simulator.getStatus());
  }
}
