package box;

class RecMain {

  public static void main(String args[]) {
    box.BoxSet boxSet = new box.SimpleBoxSet();

    boxSet.add(10, 20, 50, 30);     // Add a rectangle at (10, 20) with width 50 and height 30
    boxSet.subtract(20, 25, 30, 20); // Subtract a rectangle at (20, 25) with width 30 and height 20

    int[][] rectangles = boxSet.getBoxes();
    System.out.println("Rectangles in the set:");
    for (int[] rect : rectangles) {
      System.out.println(
          "x: " + rect[0] + ", y: " + rect[1] + ", width: " + rect[2] + ", height: " + rect[3]);
    }
  }
}
