package milk_and_honey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Purpose: Run application •Main method is located here •A new User thread is
 * created for each user here
 * 
 * @author Paige Jones
 */

public class Driver {

	public static void main(String[] args) throws ProductDatabaseFileException {

		// Load and process product database
		String fileName = "./src/database/product_database.csv";
		ArrayList<Item> productList = new ArrayList<Item>();
		ConcurrentHashMap<String, ArrayList<Item>> productLookUp = new ConcurrentHashMap<String, ArrayList<Item>>();
		SQLHelper productDbHelper = new SQLHelper(Constants.DB_URL);
		productDbHelper.connect();
		System.out.println("Connection to SQLite database is established");
		// The try section contains pieces of code that is known to throw unchecked
		// exception. If such code is not placed in
		// the try block then you get compile time error
		try {

			ProductDbCreator.processProductDatabase(fileName, productDbHelper, productList, productLookUp);
		}
		// A try block should always be followed by a catch block. When an exception is
		// thrown by a piece of code in the try
		// block, the control comes to the associated catch block. The catch block
		// basically tells Java what to do when an exception of a
		// specific type occurs. The below catch statement is called a multi-catch
		// statement as one catch statement is applicable to multiple types of
		// exceptions.
		// THis ensures that we don't have to write one catch block for each exception.
		// IOException, InvalidProductTypeException and InsufficientAttributeException
		// are caught below. What happens in the catch block is to the programmer. The
		// programmer can just choose to display an error message and continue the
		// execution of the code. Alternatively, the programmer can choose to exit the
		// program either by throwing another form of exception (as shown below)
		// or using the exit() function
		catch (IOException | InvalidProductTypeException | InsufficientAttributeException e) {
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
