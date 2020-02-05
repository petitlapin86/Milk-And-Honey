
package milk_and_honey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

	public static ArrayList<Item> processProductDatabase(String fileName)
			throws IOException, InvalidProductTypeException, InsufficientAttributeException {
		// Read products from database .csv file
		ArrayList<Item> productList = new ArrayList<Item>();

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
					System.out.println(" Shampoo item was added with following info:");
					System.out.println(shampooObject);
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
					System.out.println("\n Moisturizer item was added with following info:");
					System.out.println(moisturizerObject);

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
					System.out.println("\n Body Oil item was added with following info:");
					System.out.println(bodyoilObject);
				} else {
					throw new InvalidProductTypeException(entries[0] + " is an invalid product type");
				}

			}
		}
		return productList;
	}

	public static void main(String[] args) throws ProductDatabaseFileException {

		Scanner input = new Scanner(System.in);

		// Ask User for name
		System.out.print("Welcome! What is your name?: ");
		String myString = input.next();
		System.out.println("Hey " + myString + ", Thanks for stopping by the Milk & Honey Store. \n");

		// Print Introduction to console
		System.out.println(
				"All of our products are created by simple, whole ingredients found in nature. \nIn an effort to lower our carbon footprint we work with farms located within \nthe state of Vermont (where you'll find us!) Check out our product list: \n");

		// Load and process product database
		String fileName = "./src/database/product_database.csv";
		ArrayList<Item> productList = new ArrayList<Item>();
		try {
			productList = processProductDatabase(fileName);
		} catch (IOException | InvalidProductTypeException | InsufficientAttributeException e) {
			// e.printStackTrace();
			// throw new ProductDatabaseFileException("Inconsistency in the product database
			// csv");
			throw new ProductDatabaseFileException(e.getMessage());

		}

		// Ask User if they would like to make a purchase
		System.out.print("\n Would you like to add an item to your cart? Y/N: ");
		String answer = input.next();
		if (answer.equals("Y"))
			System.out.println(" Okay great! Lets go shopping \n");
		else
			System.out.println(" Thanks for browsing, goodbye. \n");

		// TODO create selection of products
		// TODO LOOP through shopping experience
		// until user is finished adding items to cart

	}
}
