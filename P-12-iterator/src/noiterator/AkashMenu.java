package noiterator;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Akash menu.
 */
public class AkashMenu {
  private List<MenuItem> menuItems;

  /**
   * Instantiates a new Akash menu.
   */
  public AkashMenu() {
    this.menuItems = new ArrayList<>();
  }

  /**
   * Add a new menu item to the menu.
   *
   * @param name        the name of the menu item.
   * @param description the description of the item.
   * @param vegetarian  whether the item is vegetarian.
   * @param price       the item's price.
   */
  public void addItem(String name, String description, boolean vegetarian, double price) {
    MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
    menuItems.add(menuItem);
  }

  /**
   * Get all menu items stored in the menu.
   *
   * @return a list of menu items.
   */
  public List<MenuItem> getMenuItems() {
    return menuItems;
  }
}
