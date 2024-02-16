/**
 * MainMoney is the main class.
 */

public class MainMoney {

  public static void main(String[] args) {
    SimpleMoney m = new SimpleMoney(3, 20);
    SimpleMoney m1 = new SimpleMoney(30, 99);
    /**
     * Calling the methods from the SimpleMoney class and printing the output.
     */
    System.out.println(m.add(m1));
    System.out.println(m.add(3, 20));
  }
}
