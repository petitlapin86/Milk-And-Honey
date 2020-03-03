
package milk_and_honey;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Purpose:
 * 
 * @author Paige Jones
 */

public class RetailWebsite {

	private ArrayList<Item> productList;
	private HashMap<String, ArrayList<Item>> productLookUp;
	private Cart userCart;
	private SQLHelper userDbHelper;

	public RetailWebsite() {

		userCart = new Cart();

		userDbHelper = new SQLHelper(Constants.DB_URL);
		productList = userDbHelper.getAllProductList(Constants.ALL_TABLE);
		productLookUp = new HashMap<String, ArrayList<Item>>();

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

	// WE have encountered reading from file before. For example in order to load
	// the product information we read the porduct_database.csv. The problem of
	// creating objects of ITem subclass from the csv is that we need to explicitly
	// process each token in a line and assign it to the corresponding variables in
	// the Item subclasses. This method is a bit tedious owing to the explicit
	// processing of tokens. For circumstances wherein the object of the
	// corresponding classes are created and we would like to store some information
	// into file, we could get away by simply dumping the objects into a file. The
	// objects are written into the file in a way such that when the file is read,
	// objects in the file can be loaded automatically to objects of the
	// corresponding classes without any explicit processing. THis mechanism of
	// writing and reading of class objects is achieved using ObjectOutputSteam and
	// ObjectINputStream respectively
	private void updateInventory(Cart userCart) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create an ObjecInputStream object associated to the file where the Item
		// objects are written. The objects were written into the specified file earlier
		// in the code using ObjectOutputStream
		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(Constants.OBJECT_OUT_FILE));) {
			// Create an ObjectOutputStream object associated to the file where you would
			// like to write the unavailable Item objects
			// Note that we are neither closing objInt nor outObj after we are done using.
			// THis is because these variables are created in the try scope. As soon as the
			// try block is done these streams are automatically closed.
			try (ObjectOutputStream outObj = new ObjectOutputStream(
					new FileOutputStream("./src/database/unavailable.txt"));) {
				// keep on reading the file until you get EOFException
				while (true) {
					try {
						// readObject will read the object from the beginning of the file and keeps
						// traversing down the file
						Item currentItem = (Item) (objIn.readObject());
						if (currentItem.quantity == 0) {
							// if item quantity is 0 then write to unavailable file
							outObj.writeObject(currentItem);
						}
					} catch (EOFException ex) {
						// EOF exception implies end of file reached so come out of the while loop
						break;
					}
				}
			}
		}

	}

	private void addItemToLookUp(String itemType, Item currentItem) {

		if (!productLookUp.containsKey(itemType)) {
			productLookUp.put(itemType, new ArrayList<Item>());

		}
		productLookUp.get(itemType).add(currentItem);

	}

	// Temporary fix. Replace this with SQL queries
	private void createProductLookUp() {
		for (Item currentItem : productList) {
			addItemToLookUp(currentItem.getCategory(), currentItem);
		}
	}

	// Main Shopping Screen
	public void startShopping() {
		createProductLookUp();
		Scanner in = new Scanner(System.in);
		int choice;

		while (true) {

			System.out.println("1. Hair Care");
			System.out.println("2. Body");
			System.out.println("3. Face");
			System.out.println("4. Display All Products");
			System.out.println("5. Display limited availability items");
			System.out.println("6. Display items by price low to high");
			System.out.println("7. Display products on discount");
			System.out.println("8. Display Cart Contents");
			System.out.println("0. Exit");

			choice = in.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Below is the list of Hair Care products");
				selectProduct(Constants.SHAMPOO, in);
				break;
			case 2:
				System.out.println("Below is the list of Body products");
				selectProduct(Constants.BODYOIL, in);
				break;
			case 3:
				System.out.println("Below is the list of Face products");
				selectProduct(Constants.MOISTURIZER, in);
				break;
			case 4:
				displayAllProducts(productList);
				break;
			case 5:
				System.out.println("Hurry!!, only few left");
				displayLimitedAvailablItems();
				break;
			case 6:
				System.out.println("Products in the order of low to high price");
				ArrayList<Item> resultList = userDbHelper.priceLowToHigh(Constants.ALL_TABLE);
				displayAllProducts(resultList);
				break;
			case 7:
				System.out.println("THe following products are on discount");
				userDbHelper.displayAllDiscountedProducts(Constants.DISCOUNT_PRODUCTS, Constants.ALL_TABLE);
				break;
			case 8:
				userCart.displayCart();
				break;
			case 0:
				System.out.println("Thank you for shooping at Milk and Honey");
				try {
					updateInventory(userCart);
//					userDbHelper.dropTable(Constants.ALL_TABLE);
//					userDbHelper.dropTable(Constants.DISCOUNT_PRODUCTS);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				return;
			default:
				System.out.println("Invalid choice. PLease enter one of the options from the following menu");
			}

		}

	}

	// Lambdas : lambdas are inline and temporary variables that are used to write
	// inline function codes. THe format for declaring the lambda is
	// lamabda_variable
	// =>
	// Note that lambda variables need not be declared, just the variable name is
	// enough. THink of lambda variables as inputs to the function. If a function
	// code is only a couple of lines then instead of creating a separate function
	// and populating it , it is better to used lambda variables and create an
	// inline
	// function
	// It is also possible to have more than one lambda variable w,v -> function
	// code using w and v
	// Stream : Stream is used on array like datastructures that contain a sequence
	// of objects. Applying a stream on an arraylist as shown below causes the
	// functions following the stream to be applied once for every element of the
	// arraylist
	private void displayLimitedAvailablItems() {
		productList.stream() // convert the given array into sequence of objects to be fed into the following
								// operations
				.filter(w -> (w.getQuantity() <= Constants.LIMITED_AVAILABILTY_QUANTIY)) // for each object coming from
																							// stream
				// check if the specified condition is
				// met
				.forEach(System.out::println);

	}

	private void displayItemsOfGivenType(String type) {
		int i = 1;
		for (Item currentItem : productLookUp.get(type)) {
			System.out.println((i++) + ". " + currentItem);
		}
	}

	private void displayAllProducts(ArrayList<Item> inputList) {
		System.out.println("Below is tour product list");
		int i = 1;
		for (Item currentItem : inputList) {
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
