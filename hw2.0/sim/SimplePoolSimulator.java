/**
 * The SimplePoolSimulator class implements the PoolSimulator interface and provides a simulation of
 * a ball on a pool table with either simple or friction-based physics.
 */
public class SimplePoolSimulator implements PoolSimulator {

  // Class variables
  private final int COLLISION_FRICTION = -5;
  private final double FRICTION = -0.1;
  private final double GRAVITY = 9.81;
  private int tableWidth;
  private int tableHeight;
  private int x;
  private int y;
  private int speed;
  private int radius;
  private double dx;
  private double dy;
  private String status;
  private String physicsType;

  /**
   * Constructs a SimplePoolSimulator with the specified table dimensions and physics type.
   *
   * @param width  The width of the pool table.
   * @param height The height of the pool table.
   * @param type   The type of physics to be used ('simple' or 'friction').
   * @throws IllegalArgumentException If the provided parameters are invalid.
   */
  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Table Width Is Invalid");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Table Height Is Invalid");
    } else if (!type.equalsIgnoreCase("simple") && !type.equalsIgnoreCase("friction")) {
      throw new IllegalArgumentException("Physics Type Is Invalid");
    }

    this.tableWidth = width;
    this.tableHeight = height;
    this.physicsType = type;
    this.x = (int) Double.NEGATIVE_INFINITY;
    this.y = (int) Double.NEGATIVE_INFINITY;
    this.radius = (int) Double.NEGATIVE_INFINITY;
    this.dx = 0;
    this.dy = 0;
    this.speed = 0;
    this.status = "Ball not set up";
  }

  /**
   * Sets up the initial conditions of the ball for the simulation.
   *
   * @param x      The initial x-coordinate of the ball.
   * @param y      The initial y-coordinate of the ball.
   * @param radius The radius of the ball.
   * @param speed  The initial speed of the ball.
   * @param dx     The x-component of the ball's direction vector.
   * @param dy     The y-component of the ball's direction vector.
   * @throws IllegalArgumentException If the provided parameters are invalid.
   */
  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
      throws IllegalArgumentException {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius Is Invalid.");
    } else if (y - radius < 0 || y + radius > this.tableHeight) {
      throw new IllegalArgumentException("Y Is Invalid.");
    } else if (x - radius < 0 || x + radius > this.tableWidth) {
      throw new IllegalArgumentException("X Is Invalid.");
    } else if (speed < 0) {
      throw new IllegalArgumentException("Speed Is Invalid.");
    } else if (dx == 0 && dy == 0) {
      throw new IllegalArgumentException("dx and dy both can not be zero.");
    }

    this.x = x;
    this.y = y;
    this.dx = dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    this.dy = dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    this.radius = radius;
    this.speed = speed;
    this.status = "Simulation started";
  }

  /**
   * Advances the simulation of the ball's motion based on the selected physics type.
   */
  @Override
  public void advance() {
    if (this.status.equals("Ball not set up") || this.status.equals("Ball is stationary")) {
      return;
    }
    // Call the appropriate method based on physics type
    if (physicsType.equalsIgnoreCase("simple")) {
      advanceSimple();
    } else if (physicsType.equalsIgnoreCase("friction")) {
      advanceFriction();
    }
  }

  /**
   * advanceSimple() advances the simulation of the ball's motion based on the simple physics.
   */
  private void advanceSimple() {
    double minimumTime = Double.POSITIVE_INFINITY;
    boolean flagForX = false;
    boolean flagForY = false;

    double timeToRightEdge = ((tableWidth - this.radius - this.x) / (this.speed * this.dx));
    //tableWidth becomes 0 for left edge
    double timeToLeftEdge = ((-this.radius - this.x) / (this.speed * this.dx));
    double timeToTopEdge = ((tableHeight - this.radius - this.y) / (this.speed * this.dy));
    //tableHeight becomes 0 for bottom edge
    double timeToBottomEdge = ((-this.radius - this.y) / (this.speed * this.dy));

    if (timeToRightEdge > 0 && timeToRightEdge < minimumTime) {
      minimumTime = timeToRightEdge;
      flagForX = true;
      this.status = "Ball hit right edge";
    }

    if (timeToLeftEdge > 0 && timeToLeftEdge < minimumTime) {
      minimumTime = timeToLeftEdge;
      flagForX = true;
      this.status = "Ball hit left edge";
    }

    if (timeToTopEdge > 0 && timeToTopEdge < minimumTime) {
      minimumTime = timeToTopEdge;
      flagForY = true;
      flagForX = false;
      this.status = "Ball hit top edge";
    }

    if (timeToBottomEdge > 0 && timeToBottomEdge < minimumTime) {
      minimumTime = timeToBottomEdge;
      flagForY = true;
      flagForX = false;
      this.status = "Ball hit bottom edge";
    }

    this.x += (int) (this.speed * this.dx * minimumTime);

    if (this.x > (tableWidth - this.radius)) {
      this.x = tableWidth - this.radius;
    } else if (this.x < this.radius) {
      this.x = this.radius;
    }

    this.y += (int) (this.speed * this.dy * minimumTime);
    if (this.y > (tableHeight - this.radius)) {
      this.y = tableHeight - this.radius;
    } else if (this.y < this.radius) {
      this.y = this.radius;
    }

    this.speed = this.speed + COLLISION_FRICTION;

    if (flagForX) {
      if (this.dx > 0 && this.x < (tableWidth - this.radius)) {
        this.status = "Ball is stationary";
      } else if (this.dx < 0 && this.x > this.radius) {
        this.status = "Ball is stationary";
      }
      this.dx *= -1;
    }

    if (flagForY) {
      if (this.dy > 0 && this.y < (tableHeight - this.radius)) {
        this.status = "Ball is stationary";
      } else if (this.dy < 0 && this.y > this.radius) {
        this.status = "Ball is stationary";
      }
      this.dy *= -1;
    }

    if (this.speed <= 0) {
      this.status = "Ball is stationary";
    }
  }

  /**
   * advanceFriction() advances the simulation of the ball's motion based on the friction-based
   * physics.
   */
  private void advanceFriction() {
    boolean flagForX = false;
    boolean flagForY = false;
    double minimumTime = -(this.speed / (GRAVITY * FRICTION));

    double rightRoot = (Math.pow((this.speed * this.dx), 2) - (4 * (
        ((GRAVITY * FRICTION * this.dx) / 2) * (this.tableWidth - this.radius * this.x))));
    double leftRoot = (Math.pow((this.speed * this.dx), 2) - (4 * (
        ((GRAVITY * FRICTION * this.dx) / 2) * (-this.radius * this.x))));
    double topRoot = (Math.pow((this.speed * this.dy), 2) - (4 * (
        ((GRAVITY * FRICTION * this.dy) / 2) * (this.tableHeight - this.radius * this.y))));
    double bottomRoot = (Math.pow((this.speed * this.dy), 2) - (4 * (
        ((GRAVITY * FRICTION * this.dy) / 2) * (-this.radius * this.y))));

    if (rightRoot > 0) {
      double root1 =
          (2 * (this.tableWidth - this.radius - this.x)) / ((this.speed * this.dx) + Math.sqrt(
              rightRoot));
      double root2 =
          (2 * (this.tableWidth - this.radius - this.x)) / ((this.speed * this.dx) - Math.sqrt(
              rightRoot));

      if (root1 > 0 && root1 < minimumTime) {
        minimumTime = root1;
        flagForX = true;
        this.status = "Ball hit right edge";
      } else if (root2 > 0 && root2 < minimumTime) {
        minimumTime = root2;
        flagForX = true;
        this.status = "Ball hit right edge";
      }
    }

    if (leftRoot > 0) {
      double root1 = (2 * (-this.radius - this.x)) / ((this.speed * this.dx) + Math.sqrt(leftRoot));
      double root2 = (2 * (-this.radius - this.x)) / ((this.speed * this.dx) - Math.sqrt(leftRoot));

      if (root1 > 0 && root1 < minimumTime) {
        minimumTime = root1;
        flagForX = true;
        this.status = "Ball hit left edge";
      } else if (root2 > 0 && root2 < minimumTime) {
        minimumTime = root2;
        flagForX = true;
        this.status = "Ball hit left edge";
      }
    }

    if (topRoot > 0) {
      double root1 =
          (2 * (this.tableHeight - this.radius - this.y)) / ((this.speed * this.dy) + Math.sqrt(
              topRoot));
      double root2 =
          (2 * (this.tableHeight - this.radius - this.y)) / ((this.speed * this.dy) - Math.sqrt(
              topRoot));

      if (root1 > 0 && root1 < minimumTime) {
        minimumTime = root1;
        flagForY = true;
        flagForX = false;
        this.status = "Ball hit top edge";
      } else if (root2 > 0 && root2 < minimumTime) {
        minimumTime = root2;
        flagForY = true;
        flagForX = false;
        this.status = "Ball hit top edge";
      }
    }

    if (bottomRoot > 0) {
      double root1 =
          (2 * (-this.radius - this.y)) / ((this.speed * this.dy) + Math.sqrt(bottomRoot));
      double root2 =
          (2 * (-this.radius - this.y)) / ((this.speed * this.dy) - Math.sqrt(bottomRoot));

      if (root1 > 0 && root1 < minimumTime) {
        minimumTime = root1;
        flagForY = true;
        flagForX = false;
        this.status = "Ball hit bottom edge";
      } else if (root2 > 0 && root2 < minimumTime) {
        minimumTime = root2;
        flagForY = true;
        flagForX = false;
        this.status = "Ball hit bottom edge";
      }
    }

    this.x += (int) ((this.speed * this.dx * minimumTime)
        - ((GRAVITY * FRICTION * this.dx) / 2) * Math.pow(minimumTime, 2));
    if (this.x > (tableWidth - this.radius)) {
      this.x = tableWidth - this.radius;
    } else if (this.x < this.radius) {
      this.x = this.radius;
    }

    this.y += (int) ((this.speed * this.dy * minimumTime)
        - ((GRAVITY * FRICTION * this.dy) / 2) * Math.pow(minimumTime, 2));
    if (this.y > (tableHeight - this.radius)) {
      this.y = tableHeight - this.radius;
    } else if (this.y < this.radius) {
      this.y = this.radius;
    }

    this.speed = (int) (this.speed + (GRAVITY * FRICTION * minimumTime));

    if (flagForX) {
      if (this.dx > 0 && this.x < (tableWidth - this.radius)) {
        this.status = "Ball is stationary";
      } else if (this.dx < 0 && this.x > this.radius) {
        this.status = "Ball is stationary";
      }
      this.dx *= -1;
    }

    if (flagForY) {
      if (this.dy > 0 && this.y < (tableHeight - this.radius)) {
        this.status = "Ball is stationary";
      } else if (this.dy < 0 && this.y > this.radius) {
        this.status = "Ball is stationary";
      }
      this.dy *= -1;
    }

    if (this.speed <= 0) {
      this.status = "Ball is stationary";
    }
  }

  /**
   * Retrieves the width of the pool table.
   *
   * @return The width of the pool table.
   */
  @Override
  public int getTableWidth() {
    return this.tableWidth;
  }

  /**
   * Retrieves the height of the pool table.
   *
   * @return The height of the pool table.
   */
  @Override
  public int getTableHeight() {
    return this.tableHeight;
  }

  /**
   * Retrieves the current x-coordinate of the ball.
   *
   * @return The x-coordinate of the ball.
   */
  @Override
  public double getBallPositionX() {
    return this.x;
  }

  /**
   * Retrieves the current y-coordinate of the ball.
   *
   * @return The y-coordinate of the ball.
   */
  @Override
  public double getBallPositionY() {
    return this.y;
  }

  /**
   * Retrieves the radius of the ball.
   *
   * @return The radius of the ball.
   */
  @Override
  public double getBallRadius() {
    return this.radius;
  }

  /**
   * Retrieves the x-component of the velocity of the ball.
   *
   * @return The x-component of the velocity of the ball.
   */
  @Override
  public double getBallVelocityX() {
    return this.speed * this.dx;
  }

  /**
   * Retrieves the y-component of the velocity of the ball.
   *
   * @return The y-component of the velocity of the ball.
   */
  @Override
  public double getBallVelocityY() {
    return this.speed * this.dy;
  }

  /**
   * Retrieves the current status of the simulation.
   *
   * @return The status of the simulation.
   */
  @Override
  public String getStatus() {
    return "Status: " + this.status;
  }
}
