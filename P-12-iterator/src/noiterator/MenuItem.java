package noiterator;

/**
 * 
 * Save information about menu items.
 *
 */
public class MenuItem {
  String name;
  String description;
  boolean vegetarian;
  double price;

  /**
   * Constructor for menu item.
   * 
   * @param name the name of the menu item.
   * @param description the description of the item.
   * @param vegetarian whether the item is vegetarian.
   * @param price the item's price.
   */
  public MenuItem(String name, String description, boolean vegetarian, double price) {
    this.name = name;
    this.description = description;
    this.vegetarian = vegetarian;
    this.price = price;
  }
  
  /**
   * Get item's name.
   * 
   * @return item's name.
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Get item's description.
   * 
   * @return item's description.
   */
  public String getDescription() {
    return this.description;
  }
  
  /**
   * Get item's price.
   * 
   * @return item's price.
   */
  public double getPrice() {
    return this.price;
  }
  
  /**
   * Get information on whether item is vegetarian.
   * 
   * @return is vegetarian flag.
   */
  public boolean isVegetarian() {
    return this.vegetarian;
  }

}
