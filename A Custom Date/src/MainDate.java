/**
 * MainDate is the main class.
 */
public class MainDate {

  public static void main(String[] args) {

    /**
     * Calling all the methods from the MyDate class and printing the output.
     */
    MyDate date = new MyDate(30, 1, 1800);
    MyDate date1 = new MyDate(29, 2, 2024);
    MyDate date2 = new MyDate(1, 1, 0);
    date.advance(3);
    System.out.println(date);
    date.advance(-10);
    System.out.println(date);
    date1.advance(-10);
    System.out.println(date1);
    date2.advance(-15);
    date2.advance(20);
    System.out.println(date2);
  }
}
