package milk_and_honey;

import java.util.ArrayList;

/**
 * Cart has methods involving product quantities and prices
 *
 * @author Paige Jones
 * 
 *         TODO finish cart implementation
 */

public class Cart {

	protected ArrayList<Item> cart;
	protected int quantity; // total number of items in the cart
	protected double total; // total price of items in the cart
	protected double price; // price of individual item

	// Create an empty shopping cart
	public Cart() {
		cart = new ArrayList<Item>();
		quantity = 0;
		total = 0.0;
		price = 0.0;
	}

	// Adds an item to the shopping cart.
	public void addToCart(String name, double price, int quantity) {
		// TODO
	}

	// add given item to cart
	public void addToCart(Item item) {
		cart.add(item);
	}

	public void removeFromCart(Item item) {

	}

	// Gets the total cost of cart
	public double getTotalCartValue() {
		total = (price * quantity);
		return total;
	}

	// Gets all the items in cart
	public ArrayList<Item> getItems() {
		// TODO Auto-generated method stub
		return cart;
	}

	// Gets items in the cart of a specific class
	public <T extends Item> ArrayList<T> getItemsByClass(Class<T> itemType) {
		ArrayList<T> itemsOfGivenClass = new ArrayList<T>();

		for (Item cartItem : cart) {
			if (itemType.isInstance(cartItem)) {
				itemsOfGivenClass.add(itemType.cast(cartItem));
			}
		}

		return itemsOfGivenClass;

	}

	public void displayCart() {
		if (cart.size() == 0)
			System.out.println("No items to display. Empty cart !!");
		else {
			System.out.println("Your cart contents are:");
			for (Item currentItem : cart) {
				System.out.println(currentItem);
			}
		}
	}

}
