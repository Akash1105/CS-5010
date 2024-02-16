import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

  private Book book;
  private Person akash;

  @Before
  public void setUp() {
    akash = new Person("Akash", "Sancheti", 1997);
    book = new Book("The forty rules of Love", akash, 16.5f);
  }

  @Test
  public void testTitle() {
    assertEquals("The forty rules of Love", book.getTitle());
  }

  @Test
  public void testAuthor() {
    assertEquals("The forty rules of Love", book.getTitle());
  }

  @Test
  public void testPrice() {
    assertEquals(16.5, book.getPrice(), 0.0);
  }
}
