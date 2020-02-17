package milk_and_honey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws ProductDatabaseFileException {

		Scanner input = new Scanner(System.in);

		RetailWebsite myWebsite = new RetailWebsite();

		// Ask User for name
		System.out.print("Welcome! What is your name?: ");
		String myString = input.next();
		System.out.println("Hey " + myString + ", Thanks for stopping by the Milk & Honey Store. \n");

		// Print Introduction to console
		System.out.println("All of our products are created by simple, whole ingredients found in nature. "
				+ "\nIn an effort to lower our carbon footprint we work with farms located within "
				+ "\nthe state of Vermont (where you'll find us!)  \n");

		// Load and process product database
		String fileName = "./src/database/product_database.csv";
		ArrayList<Item> productList = new ArrayList<Item>();
		try {
			productList = myWebsite.processProductDatabase(fileName);
		} catch (IOException | InvalidProductTypeException | InsufficientAttributeException e) {
			// e.printStackTrace();
			// throw new ProductDatabaseFileException("Inconsistency in the product database
			// csv");
			throw new ProductDatabaseFileException(e.getMessage());

		}

		// Ask User if they would like to enter the store
		System.out.print("\n Would you like to Take a look around the Milk & Honey Store? Y/N: ");
		String answer = input.next();
		if (answer.equals("Y") || answer.equals("y")) {
			System.out.println(" Okay great! Lets go shopping \n");
			myWebsite.startShopping();
		} else
			System.out.println(" Thanks for browsing, goodbye. \n");

		// TODO create selection of products
		// TODO LOOP through shopping experience
		// until user is finished adding items to cart

	}

}
