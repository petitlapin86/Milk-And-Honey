
package milk_and_honey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main class is located in this file this will be the main hub of my program
 * 
 * @author Paige Jones
 */

public class RetailWebsite {

	private static final String SHAMPOO = "Shampoo";
	private static final String MOISTURIZER = "Moisturizer";
	private static final String BODYOIL = "BodyOil";
	private static final int MINIMUM_ATTRIBUTES_COUNT = 8;

	private ArrayList<Item> productList;
	private HashMap<String, ArrayList<Item>> productLookUp;
	private Cart userCart;

	public RetailWebsite() {
		productList = new ArrayList<Item>();
		productLookUp = new HashMap<String, ArrayList<Item>>();
		userCart = new Cart();

	}

	// Function to compute discount based on user cart
	double discountComputer(Cart userCart) {
		// if the cart total is greater than $100 and at least one shampoo
		// is in cart then a 10% discount is awarded.
		double totalCartValue = userCart.getTotalCartValue();
		int numOfShampoos = 0;
		for (Item item : userCart.getItems()) {
			// Use of polymorphism is shown here
			if (item.getType().equals("Shampoo")) {
				numOfShampoos++;
				break;
			}
		}
		if (totalCartValue >= 100 && numOfShampoos >= 1) {
			return 0.1 * totalCartValue;
		} else {
			return 0;
		}
	}

	public ArrayList<Item> getShampoosThatThicken(ArrayList<Item> itemList) {
		ArrayList<Item> thickShampoos = new ArrayList<Item>();

		for (Item currentItem : itemList) {
			if (currentItem instanceof Shampoo) {
				if (((Shampoo) currentItem).thickeningShampoo()) {
					thickShampoos.add(currentItem);
				}
			}

		}
		return thickShampoos;
	}

	private void addItemToLookUp(String itemType, Item currentItem) {

		if (!productLookUp.containsKey(itemType)) {
			productLookUp.put(itemType, new ArrayList<Item>());

		}
		productLookUp.get(itemType).add(currentItem);

	}

	public ArrayList<Item> processProductDatabase(String fileName)
			throws IOException, InvalidProductTypeException, InsufficientAttributeException {
		// Read products from database .csv file

		String line = "";
		String delimiter = ","; // comma separates the objects
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while ((line = reader.readLine()) != null) {
				String[] entries = line.split(delimiter);
				int numOfEntries = entries.length;
				if (numOfEntries < MINIMUM_ATTRIBUTES_COUNT) {
					throw new InsufficientAttributeException(
							"A minimum of " + MINIMUM_ATTRIBUTES_COUNT + " attributes are rquired. However \" " + line
									+ "\" has only " + numOfEntries + " attributes");
				}
				String name = entries[1];
				double price = Double.parseDouble(entries[2]);
				String description = entries[3];
				final int numOfIngredients = Integer.parseInt(entries[4]);
				String[] ingredients = new String[numOfIngredients];
				int startIngredientEntry = 5;
				for (int i = 0; i < numOfIngredients; i++) {
					ingredients[i] = entries[startIngredientEntry + i];
				}
				int size = Integer.parseInt(entries[startIngredientEntry + numOfIngredients]);

				if (entries[0].equals(SHAMPOO)) { // If the first word in the line is shampoo
					String hairType = entries[numOfEntries - 2];
					boolean hairGrowth = false; // assume hairgrowth is false
					if (entries[numOfEntries - 1].equals("Y")) { // unless a Y is indicated in the .csv file
						hairGrowth = true; // then assign to true
					}
					// then create a new shamoo object with these params
					Shampoo shampooObject = new Shampoo(name, price, description, ingredients, size, hairType,
							hairGrowth);
					productList.add(shampooObject);
					addItemToLookUp(SHAMPOO, shampooObject);
//					System.out.println(" Shampoo item was added with following info:");
//					System.out.println(shampooObject);
				} else if (entries[0].equals(MOISTURIZER)) { // Or If the first word in the line is moisturizer

					String skinType = entries[numOfEntries - 2];
					boolean acnePreventor = false;
					if (entries[numOfEntries - 1].equals("Y")) {

						acnePreventor = true;
					}
					// then create a new moisturizer object with these params
					Moisturizer moisturizerObject = new Moisturizer(name, price, description, ingredients, size,
							skinType, acnePreventor);
					productList.add(moisturizerObject);
					addItemToLookUp(MOISTURIZER, moisturizerObject);
//					System.out.println("\n Moisturizer item was added with following info:");
//					System.out.println(moisturizerObject);

				} else if (entries[0].equals(BODYOIL)) { // Or If the first word in the line is body oil

					String bodyConcern = entries[numOfEntries - 2];
					boolean forDrySkin = false;
					if (entries[numOfEntries - 1].equals("Y")) {
						forDrySkin = true;
					}
					// then create a new body oil object with these params
					BodyOil bodyoilObject = new BodyOil(name, price, description, ingredients, size, bodyConcern,
							forDrySkin);
					productList.add(bodyoilObject);
					addItemToLookUp(BODYOIL, bodyoilObject);
//					System.out.println("\n Body Oil item was added with following info:");
//					System.out.println(bodyoilObject);
				} else {
					throw new InvalidProductTypeException(entries[0] + " is an invalid product type");
				}

			}
		}
		return productList;
	}

	// Main Shopping Screen
	public void startShopping() {

		Scanner in = new Scanner(System.in);
		int choice;

		while (true) {

			System.out.println("1. Hair Care");
			System.out.println("2. Body");
			System.out.println("3. Face");
			System.out.println("4. Display All Products");
			System.out.println("5. Display Cart Contents");
			System.out.println("0. Exit");

			choice = in.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Below is the list of Hair Care products");
				selectProduct(SHAMPOO, in);
				break;
			case 2:
				System.out.println("Below is the list of Body products");
				selectProduct(BODYOIL, in);
				break;
			case 3:
				System.out.println("Below is the list of Face products");
				selectProduct(MOISTURIZER, in);
				break;
			case 4:
				displayAllProducts();
				break;
			case 5:
				userCart.displayCart();
				break;
			case 0:
				System.out.println("Thank you for shooping at Milk and Honey");
				return;
			default:
				System.out.println("Invalid choice. PLease enter one of the options from the following menu");
			}

		}
	}

	private void displayItemsOfGivenType(String type) {
		int i = 1;
		for (Item currentItem : productLookUp.get(type)) {
			System.out.println((i++) + ". " + currentItem);
		}
	}

	private void displayAllProducts() {
		System.out.println("Below is tour product list");
		int i = 1;
		for (Item currentItem : productList) {
			System.out.println((i++) + ". " + currentItem);
		}
	}

	private void selectProduct(String itemType, Scanner in) {
		displayItemsOfGivenType(itemType);
		int choice;

		while (true) {

			System.out.println("1. Add item to cart");
			System.out.println("2. Remove item from cart");
			System.out.println("0. Back to Main Menu");

			choice = in.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Please enter the item number to add to cart");
				int itemId = in.nextInt();
				Item itemToAdd = productLookUp.get(itemType).get(itemId - 1);
				userCart.addToCart(itemToAdd);
				break;
			case 2:
				// To-do implement this
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid choice. PLease enter one of the options from the following menu");
			}
		}

	}

}
