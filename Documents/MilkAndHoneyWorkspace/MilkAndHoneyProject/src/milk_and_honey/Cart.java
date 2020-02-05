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

	protected Item[] cart;
	protected int quantity; // total number of items in the cart
	protected double total; // total price of items in the cart
	protected double price; // price of individual item

	// Create an empty shopping cart
	public Cart() {
		cart = new Item[0];
		quantity = 0;
		total = 0.0;
		price = 0.0;
	}

	// Adds an item to the shopping cart.
	public void addToCart(String name, double price, int quantity) {
		// TODO
	}

	// Gets the total cost of cart
	public double getTotalCartValue() {
		total = (price * quantity);
		return total;
	}

	// Gets all the items in cart
	public ArrayList<Item> getItems() {
		// TODO Auto-generated method stub
		return new ArrayList<Item>();
	}

}
