
package milk_and_honey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class is located in this file
 * 
 * @author Paige Jones
 */

public class RetailWebsite {

	private static final String SHAMPOO = "Shampoo";
	private static final String MOISTURIZER = "Moisturizer";
	private static final String BODYOIL = "BodyOil";

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

	public static void main(String[] args) {

		ArrayList<Item> productList = new ArrayList<Item>();
		String fileName = "/Users/paige/Documents/MilkAndHoneyWorkspace/MilkAndHoneyProject/src/database/product_database.csv";
		String line = "";
		String delimiter = ",";
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while ((line = reader.readLine()) != null) {
				String[] entries = line.split(delimiter);
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
				int numOfEntries = entries.length;
				if (entries[0].equals(SHAMPOO)) {
					String hairType = entries[numOfEntries - 2];
					boolean hairGrowth = false;
					if (entries[numOfEntries - 1].equals("Y")) {
						hairGrowth = true;
					}
					Shampoo shampooObject = new Shampoo(name, price, description, ingredients, size, hairType,
							hairGrowth);
					productList.add(shampooObject);
					System.out.println("Shampoo item was added with following info:");
					System.out.println(shampooObject);
				} else if (entries[0].equals(MOISTURIZER)) {

					String skinType = entries[numOfEntries - 2];
					boolean acnePreventor = false;
					if (entries[numOfEntries - 1].equals("Y")) {
						acnePreventor = true;
					}
					Moisturizer moisturizerObject = new Moisturizer(name, price, description, ingredients, size,
							skinType, acnePreventor);
					productList.add(moisturizerObject);
					System.out.println("Moisturizer item was added with following info:");
					System.out.println(moisturizerObject);

				} else if (entries[0].equals(BODYOIL)) {

					String skinType = entries[numOfEntries - 2];
					boolean forDrySkin = false;
					if (entries[numOfEntries - 1].equals("Y")) {
						forDrySkin = true;
					}
					BodyOil bodyoilObject = new BodyOil(name, price, description, ingredients, size, skinType,
							forDrySkin);
					productList.add(bodyoilObject);
					System.out.println("Body Oil item was added with following info:");
					System.out.println(bodyoilObject);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
