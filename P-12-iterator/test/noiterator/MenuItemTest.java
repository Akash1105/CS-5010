package noiterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The type Menu item test.
 */
public class MenuItemTest {

  /**
   * Test menu item creation.
   */
  @Test
  public void testMenuItemCreation() {
    String name = "Soup";
    String description = "A warm and comforting starter";
    boolean vegetarian = true;
    double price = 5.99;

    MenuItem menuItem = new MenuItem(name, description, vegetarian, price);

    assertEquals(name, menuItem.getName());
    assertEquals(description, menuItem.getDescription());
    assertTrue(menuItem.isVegetarian());
    assertEquals(price, menuItem.getPrice(), 0.0);
  }

}


