package sim;

/**
 * This interface represents a pool simulator. It is used to simulate the motion of a ball on a pool
 * table.
 */
public interface PoolSimulator {

  /**
   * Starts the simulation with a ball at the given position, radius, and velocity.
   *
   * @param x      The x-coordinate of the initial position of the ball.
   * @param y      The y-coordinate of the initial position of the ball.
   * @param radius The radius of the ball.
   * @param speed  The magnitude of the initial velocity of the ball.
   * @param dx     The x-component of the direction vector of the ball's velocity.
   * @param dy     The y-component of the direction vector of the ball's velocity.
   * @throws IllegalArgumentException If any of the parameters are invalid.
   */
  void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException;

  /**
   * Advances the simulation by one unit of time.
   */
  void advance();

  /**
   * Returns the width of the table.
   *
   * @return The width of the table.
   */
  int getTableWidth();

  /**
   * Returns the height of the table.
   *
   * @return The height of the table.
   */
  int getTableHeight();

  /**
   * Returns the x-coordinate of the center of the ball.
   *
   * @return The x-coordinate of the center of the ball.
   */
  double getBallPositionX();

  /**
   * Returns the y-coordinate of the center of the ball.
   *
   * @return The y-coordinate of the center of the ball.
   */
  double getBallPositionY();

  /**
   * Returns the radius of the ball.
   *
   * @return The radius of the ball.
   */
  double getBallRadius();

  /**
   * Returns the x-component of the velocity of the ball.
   *
   * @return The x-component of the velocity of the ball.
   */
  double getBallVelocityX();

  /**
   * Returns the y-component of the velocity of the ball.
   *
   * @return The y-component of the velocity of the ball.
   */
  double getBallVelocityY();

  /**
   * Returns the status of the simulation.
   *
   * @return The status of the simulation.
   */
  String getStatus();

}
