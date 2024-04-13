package noiterator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Akash menu test.
 */
public class AkashMenuTest {

  private AkashMenu menu;
  private String name1;
  private String description1;
  private boolean vegetarian1;
  private double price1;
  private String name2;
  private String description2;
  private boolean vegetarian2;
  private double price2;

  /**
   * Sets data.
   */
  @Before
  public void setup() {
    menu = new AkashMenu();

    name1 = "Pasta";
    description1 = "A delicious Italian dish with various sauces";
    vegetarian1 = true;
    price1 = 12.50;

    name2 = "Paneer Curry";
    description2 = "Spicy and flavorful paneer with rice";
    vegetarian1 = false;
    price2 = 14.99;
  }

  /**
   * Test add item.
   */
  @Test
  public void testAddItem() {
    menu.addItem(name1, description1, vegetarian1, price1);

    MenuItem expectedMenuItem = new MenuItem(name1, description1, vegetarian1, price1);

    List<MenuItem> actualMenuItems = menu.getMenuItems();
    MenuItem actualItem = actualMenuItems.get(0); // assuming first item

    assertEquals(expectedMenuItem.getName(), actualItem.getName());
    assertEquals(expectedMenuItem.getDescription(), actualItem.getDescription());
    assertTrue(expectedMenuItem.isVegetarian() == actualItem.isVegetarian());
    assertEquals(expectedMenuItem.getPrice(), actualItem.getPrice(), 0.0);
  }

  /**
   * Test add multiple items.
   */
  @Test
  public void testAddMultipleItems() {
    menu.addItem(name1, description1, vegetarian1, price1);
    menu.addItem(name2, description2, vegetarian2, price2);

    List<MenuItem> expectedMenuItems = new ArrayList<>();
    expectedMenuItems.add(new MenuItem(name1, description1, vegetarian1, price1));
    expectedMenuItems.add(new MenuItem(name2, description2, vegetarian2, price2));

    List<MenuItem> actualMenuItems = menu.getMenuItems();

    // Compare properties of each item in the lists
    for (int i = 0; i < expectedMenuItems.size(); i++) {
      MenuItem expectedItem = expectedMenuItems.get(i);
      MenuItem actualItem = actualMenuItems.get(i);

      assertEquals(expectedItem.getName(), actualItem.getName());
      assertEquals(expectedItem.getDescription(), actualItem.getDescription());
      assertTrue(expectedItem.isVegetarian() == actualItem.isVegetarian());
      assertEquals(expectedItem.getPrice(), actualItem.getPrice(), 0.0);
    }
  }

  /**
   * Test get menu items empty.
   */
  @Test
  public void testGetMenuItemsEmpty() {
    List<MenuItem> actualMenuItems = menu.getMenuItems();

    assertEquals(0, actualMenuItems.size());
  }
}
