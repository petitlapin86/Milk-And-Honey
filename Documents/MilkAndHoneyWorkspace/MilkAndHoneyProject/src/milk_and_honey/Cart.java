package milk_and_honey;

import java.util.ArrayList;

/**
 * Cart has methods involving product quantities and prices
 *
 * @author Paige Jones
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
	/**
	 * Function that returns items from the user's cart based on the item's category
	 * 
	 * @param <T>      Generic type that extends Item
	 * @param itemType The class type of the items to be returned
	 * @return Arraylist of specified itemType that contains the current user cart's
	 *         items of the given type
	 */
	// Generics is a way of allowing the programmer to write a peice of code that
	// can be used across varied datatypes.
	// Let's consider ArrayList<T>, ArrayLIst is a Java data structure that can
	// change it's size on the go.Now what is the type of elements that can be
	// stored in the arayLIst. The "T" in arraylist acts like a variable for holding
	// the current dataType chosen by the user. FOr example ArrayList<Item> will
	// hold objects of Item class as well as it's subclasses and ArrayList<Integer>
	// will hold
	// int values. so on and so forth.
	// In the below case we have defined Generics for the function input. Class<T>
	// is the input here, T is generic because we can give values to itemType such
	// Shampoo.class, Moisturizer,class. In each of these cases T points to the
	// class that we specified in the itemType. Note that it is not necessary to use
	// the name T. It can be given any name just like Java variable. As per Java
	// coding norm, generic types are represented by single uppercase alphabets.
	// Since
	// we picked T as the generic type for our input we need to tell the compiler
	// that T is a generic type. For instance, =how does the compiler know myVar is
	// of
	// type int. It knows so because somewhere in the beginning of the code we
	// mention int myVar. Similarly, for the generic type, we need to tell the
	// compiler that T is a generic type. WE do this by specifying <T> just before
	// the return type of the function. In the below function we have <T extends
	// Item> this goes an extra mile to say that T can onlky take types that are
	// subclasses of ITem class.
	// IT should be noted that all occurences of T will be substituted with only one
	// type. Suppose you would like to have two different types based on the given
	// circumstances then you need to define two generic types. Say T and U.

	// What is the below function doing. Suppose itemTYpe = Shampoo.class then the
	// function returns ArrayList<Shampoo> from the user cart
	public <T extends Item> ArrayList<T> getItemsByClass(Class<T> itemType) {
		ArrayList<T> itemsOfGivenClass = new ArrayList<T>();

		// for each item in thecurrent user cart
		for (Item cartItem : cart) {
			// check if the item is an instance of itemType
			if (itemType.isInstance(cartItem)) {
				// if yes then cast to itemType and add to the arraylist
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
