package milk_and_honey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Driver {

	public static void main(String[] args) throws ProductDatabaseFileException {

		// Load and process product database
		String fileName = "./src/database/product_database.csv";
		ArrayList<Item> productList = new ArrayList<Item>();
		ConcurrentHashMap<String, ArrayList<Item>> productLookUp = new ConcurrentHashMap<String, ArrayList<Item>>();
		SQLHelper productDbHelper = new SQLHelper(Constants.DB_URL);
		productDbHelper.connect();
		System.out.println("Connection to SQLite database is established");
		try {

			ProductDbCreator.processProductDatabase(fileName, productDbHelper, productList, productLookUp);
		} catch (IOException | InvalidProductTypeException | InsufficientAttributeException e) {
			// e.printStackTrace();
			// throw new ProductDatabaseFileException("Inconsistency in the product database
			// csv");
			throw new ProductDatabaseFileException(e.getMessage());

		}

		// create a User
		// To-do: create one thread for each incoming user
		User currentUser = new User();
		currentUser.start();
	}

}
