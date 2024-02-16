package sim;

public class SimplePoolSimulator implements PoolSimulator {

  private int tableWidth;
  private int tableHeight;
  String physicsType;
  private double ballPositionX = (int) Double.NEGATIVE_INFINITY;;
  private double ballPositionY = (int) Double.NEGATIVE_INFINITY;;
  private double ballRadius = (int) Double.NEGATIVE_INFINITY;;
  private double ballVelocityX;
  private double ballVelocityY;
  private double ballSpeed;
  String status;

  private final double FRICTION = -0.1;
  private final double GRAVITY = 9.81;
  private final int SPEED_REDUCTION_SIMPLE = -5;
  private final double ACCELERATION_FRICTION = (GRAVITY * FRICTION);

  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive values");
    }
    if (!type.equalsIgnoreCase("simple") && !type.equalsIgnoreCase("friction")) {
      throw new IllegalArgumentException("Type must be either 'simple' or 'friction'");
    }
    this.tableWidth = width;
    this.tableHeight = height;
    this.physicsType = type;
    this.status = "Ball not set up";
  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy) throws IllegalArgumentException {
    if (!isBallInsideTable(x, y, radius) || radius <= 0 || speed <= 0) {
      throw new IllegalArgumentException("Invalid parameters for ball setup");
    }

    // Call the appropriate method based on physics type
//    if (physicsType.equalsIgnoreCase("simple")) {
//      setupSimpleBall(x, y, radius, speed, dx, dy);
//    } else if (physicsType.equalsIgnoreCase("friction")) {
//      setupFrictionBall(x, y, radius, speed, dx, dy);
//    }
    this.ballPositionX = x;
    this.ballPositionY = y;
    this.ballRadius = radius;
    this.ballSpeed = speed;
    this.ballVelocityX = speed * (dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
    this.ballVelocityY = speed * (dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
    this.status = "Simulation started";
  }

//  private void setupSimpleBall(int x, int y, int radius, int speed, double dx, double dy) {
//    this.ballPositionX = x;
//    this.ballPositionY = y;
//    this.ballRadius = radius;
//    this.ballSpeed = speed;
//    this.ballVelocityX = speed * (dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
//    this.ballVelocityY = speed * (dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
//  }
//
//  private void setupFrictionBall(int x, int y, int radius, int speed, double dx, double dy) {
//    this.ballPositionX = x;
//    this.ballPositionY = y;
//    this.ballRadius = radius;
//    this.ballSpeed = speed;
//    this.ballVelocityX = speed * (dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
//    this.ballVelocityY = speed * (dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
//  }

  private void advanceSimple() {

    double minTime = Double.POSITIVE_INFINITY;

    double timeToRightEdge   = ((tableWidth - ballRadius - ballPositionX) / (ballSpeed * ballVelocityX));
    double timeToLeftEdge    = ((-ballRadius - ballPositionX) / (ballSpeed * ballVelocityX));
    double timeToTopEdge     = ((tableHeight - ballRadius - ballPositionY) / (ballSpeed * ballVelocityY));
    double timeToBottomEdge  = ((-ballRadius - ballPositionY) / (ballSpeed * ballVelocityY));

    int changeX = 0, changeY = 0;

    if (timeToRightEdge > 0 && timeToRightEdge < minTime) {
      minTime = timeToRightEdge;

      changeX = 1;

      this.status = "Status: Ball hit right edge";
    }

    if (timeToLeftEdge > 0 && timeToLeftEdge < minTime) {
      minTime = timeToLeftEdge;

      changeX = 1;

      this.status = "Status: Ball hit left edge";
    }

    if (timeToTopEdge > 0 && timeToTopEdge < minTime) {
      minTime = timeToTopEdge;

      changeY = 1;
      changeX = 0;

      this.status = "Status: Ball hit top edge";
    }

    if (timeToBottomEdge > 0 && timeToBottomEdge < minTime) {
      minTime = timeToBottomEdge;

      changeY = 1;
      changeX = 0;

      this.status = "Status: Ball hit bottom edge";
    }

    ballPositionX += (int) (ballSpeed * ballVelocityX * minTime);
    if (ballPositionX > (tableWidth - ballRadius)) {
      ballPositionX = tableWidth - ballRadius;
    } else if (ballPositionX < ballRadius) {
      ballPositionX = ballRadius;
    }

    ballPositionY += (int) (ballSpeed * ballVelocityY * minTime);
    if (ballPositionY > (tableHeight - ballRadius)) {
      ballPositionY = tableHeight - ballRadius;
    } else if (ballPositionY < ballRadius) {
      ballPositionY = ballRadius;
    }

    ballSpeed += SPEED_REDUCTION_SIMPLE;

    if (changeX == 1) {
      if (ballVelocityX > 0 && ballPositionX < (tableWidth - ballRadius)) {
        this.status = "Status: Ball is stationary";
      } else if (ballVelocityX < 0 && ballPositionX > ballRadius) {
        this.status = "Status: Ball is stationary";
      }
      ballVelocityX *= -1;
    }

    if (changeY == 1) {
      if (ballVelocityY > 0 && ballPositionY < (tableHeight - ballRadius)) {
        this.status = "Status: Ball is stationary";
      } else if (ballVelocityY < 0 && ballPositionY > ballRadius) {
        this.status = "Status: Ball is stationary";
      }
      ballVelocityY *= -1;
    }

    if (ballSpeed <= 0) {
      this.status = "Status: Ball is stationary";
    }

  }

  @Override
  public void advance() {
    if (physicsType.equals("Ball not set up")) {
      throw new IllegalStateException("Simulation not started. Call start() first.");
    }

    // Call the appropriate method based on physics type
    if (physicsType.equalsIgnoreCase("simple")) {
      advanceSimple();
    } else if (physicsType.equalsIgnoreCase("friction")) {
      advanceFriction();
    }
  }



//  private double calculateMinTimeSimple() {
//    double timeToRightEdge = (tableWidth - ballPositionX - ballRadius) / ballVelocityX;
//    double timeToLeftEdge = (ballPositionX - ballRadius) / Math.abs(ballVelocityX);
//    double timeToTopEdge = (tableHeight - ballPositionY - ballRadius) / ballVelocityY;
//    double timeToBottomEdge = (ballPositionY - ballRadius) / Math.abs(ballVelocityY);
//
//    double minTime = Double.POSITIVE_INFINITY;
//
//    if (timeToRightEdge > 0 && timeToRightEdge < minTime) {
//      minTime = timeToRightEdge;
//    }
//    if (timeToLeftEdge > 0 && timeToLeftEdge < minTime) {
//      minTime = timeToLeftEdge;
//    }
//    if (timeToBottomEdge > 0 && timeToBottomEdge < minTime) {
//      minTime = timeToBottomEdge;
//    }
//    if (timeToTopEdge > 0 && timeToTopEdge < minTime) {
//      minTime = timeToTopEdge;
//    }
//
//    return minTime;
//  }
//
//  private void updateBallPositionSimple(double minTime) {
//    ballPositionX += ballVelocityX * minTime;
//    ballPositionY += ballVelocityY * minTime;
//  }
//
//  private void handleEdgeCollisionsSimple(double minTime) {
//    if (minTime == calculateMinTimeSimple()) {
//      ballVelocityX *= -1;
//    }
//    if (minTime == calculateMinTimeSimple()) {
//      ballVelocityY *= -1;
//    }
//
//    ballPositionX += ballVelocityX * minTime;
//    ballPositionY += ballVelocityY * minTime;
//
//    if (minTime == calculateMinTimeSimple()) {
//      ballVelocityX *= -1;
//    }
//    if (minTime == calculateMinTimeSimple()) {
//      ballVelocityY *= -1;
//    }
//  }
//
//  private void handleSpeedReductionSimple() {
//    double speedDecrement = 0.1; // Adjust the decrement factor as needed
//    ballVelocityX *= (1 - speedDecrement);
//    ballVelocityY *= (1 - speedDecrement);
//
//    if (Math.abs(ballVelocityX) < Double.MIN_VALUE && Math.abs(ballVelocityY) < Double.MIN_VALUE) {
//      ballVelocityX = 0;
//      ballVelocityY = 0;
//    }
//  }

  private void advanceFriction() {
    double minTime = -(ballSpeed / ACCELERATION_FRICTION);

    double rightRoot = (Math.pow((ballSpeed * ballVelocityX), 2) - (4 * (((ACCELERATION_FRICTION * ballVelocityX) / 2) * (tableWidth - ballRadius * ballPositionX))));
    double leftRoot = (Math.pow((ballSpeed * ballVelocityX), 2) - (4 * (((ACCELERATION_FRICTION * ballVelocityX) / 2) * (-ballRadius * ballPositionX))));
    double topRoot = (Math.pow((ballSpeed * ballVelocityY), 2) - (4 * (((ACCELERATION_FRICTION * ballVelocityY) / 2) * (tableHeight - ballRadius * ballPositionY))));
    double bottomRoot = (Math.pow((ballSpeed * ballVelocityY), 2) - (4 * (((ACCELERATION_FRICTION * ballVelocityY) / 2) * (-ballRadius * ballPositionY))));

    boolean changeX = false;
    boolean changeY = false;

    if (rightRoot > 0) {
      double root1 = (2 * (tableWidth - ballRadius - ballPositionX)) / ((ballSpeed * ballVelocityX) + Math.sqrt(rightRoot));
      double root2 = (2 * (tableWidth - ballRadius - ballPositionX)) / ((ballSpeed * ballVelocityX) - Math.sqrt(rightRoot));

      if (root1 > 0 && root1 < minTime) {
        minTime = root1;
        changeX = true;
      } else if (root2 > 0 && root2 < minTime) {
        minTime = root2;
        changeX = true;
      }
    }

    if (leftRoot > 0) {
      double root1 = (2 * (-ballRadius - ballPositionX)) / ((ballSpeed * ballVelocityX) + Math.sqrt(leftRoot));
      double root2 = (2 * (-ballRadius - ballPositionX)) / ((ballSpeed * ballVelocityX) - Math.sqrt(leftRoot));

      if (root1 > 0 && root1 < minTime) {
        minTime = root1;
        changeX = true;
      } else if (root2 > 0 && root2 < minTime) {
        minTime = root2;
        changeX = true;
      }
    }

    if (topRoot > 0) {
      double root1 = (2 * (tableHeight - ballRadius - ballPositionY)) / ((ballSpeed * ballVelocityY) + Math.sqrt(topRoot));
      double root2 = (2 * (tableHeight - ballRadius - ballPositionY)) / ((ballSpeed * ballVelocityY) - Math.sqrt(topRoot));

      if (root1 > 0 && root1 < minTime) {
        minTime = root1;
        changeY = true;
        changeX = false;
      } else if (root2 > 0 && root2 < minTime) {
        minTime = root2;
        changeY = true;
        changeX = false;
      }
    }

    if (bottomRoot > 0) {
      double root1 = (2 * (-ballRadius - ballPositionY)) / ((ballSpeed * ballVelocityY) + Math.sqrt(bottomRoot));
      double root2 = (2 * (-ballRadius - ballPositionY)) / ((ballSpeed * ballVelocityY) - Math.sqrt(bottomRoot));

      if (root1 > 0 && root1 < minTime) {
        minTime = root1;
        changeY = true;
        changeX = false;
      } else if (root2 > 0 && root2 < minTime) {
        minTime = root2;
        changeY = true;
        changeX = false;
      }
    }

    ballPositionX += (ballSpeed * ballVelocityX * minTime) - ((ACCELERATION_FRICTION * ballVelocityX) / 2) * Math.pow(minTime, 2);
    ballPositionY += (ballSpeed * ballVelocityY * minTime) - ((ACCELERATION_FRICTION * ballVelocityY) / 2) * Math.pow(minTime, 2);

    ballSpeed = ballSpeed + (ACCELERATION_FRICTION * minTime);

    if (changeX) {
      ballVelocityX *= -1;
    }

    if (changeY) {
      ballVelocityY *= -1;
    }

    if (ballSpeed <= 0) {
      ballSpeed = 0;
    }
  }

  private void checkIfBallIsStationary() {
    if (Math.abs(ballVelocityX) < Double.MIN_VALUE && Math.abs(ballVelocityY) < Double.MIN_VALUE) {
      status = "Ball is stationary";
    }
  }

  @Override
  public int getTableWidth() {
    return tableWidth;
  }

  @Override
  public int getTableHeight() {
    return tableHeight;
  }

  @Override
  public double getBallPositionX() {
    return ballPositionX;
  }

  @Override
  public double getBallPositionY() {
    return ballPositionY;
  }

  @Override
  public double getBallRadius() {
    return ballRadius;
  }

  @Override
  public double getBallVelocityX() {
    return ballVelocityX;
  }

  @Override
  public double getBallVelocityY() {
    return ballVelocityY;
  }

  @Override
  public String getStatus() {
    return status;
  }

  private boolean isBallInsideTable(int x, int y, int radius) {
    return x + radius > 0 && x - radius < tableWidth && y + radius > 0 && y - radius < tableHeight;
  }
}
