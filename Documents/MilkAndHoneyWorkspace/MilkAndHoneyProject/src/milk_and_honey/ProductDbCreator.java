package milk_and_honey;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Purpose: Create tables for products. In-charge of loading the product
 * information from csv to SQL DB
 * 
 * @author Paige Jones
 */

public class ProductDbCreator {

	// function to add elements to the productLookUp hash-map
	private static void addItemToLookUp(ConcurrentHashMap<String, ArrayList<Item>> productLookUp, String itemType,
			Item currentItem) {

		// if the given category is not in productlookup then create an entry for the
		// given category and assign an empty arrayLIst as it's corresponding value.
		if (!productLookUp.containsKey(itemType)) {
			productLookUp.put(itemType, new ArrayList<Item>());

		}
		// Get the corresponding arrayLIst for the given category and Add the given item
		// object to the retrieved arayLIst
		productLookUp.get(itemType).add(currentItem);

	}

	// javadoc is not necessary for private functions as they are not exposed to
	// functions outside the current class
	// Note that in order to use createTables method in the processProductDatabase
	// function then the createTables has to be static
	// Given a static function, only other static functions can be invoked inside
	// it.
	// THis function creates the SQL commands to create tables and gives it to the
	// productDbHelper to execute these sql command.
	// 4 tables will be created.
	// ALL_TABLE - A table that contains all products across all categories. The
	// columns in this table = the variables defined in the ITem class (8 columns in
	// total)
	// SHAMPOO - A table that contains only Shampoo product info. The columns in
	// this table = variables in ITem + variable sin Hair + Variables in Shampoo (10
	// columns in total)
	// MOISTURIZER - A table that contains only Moisturizer product info. The
	// columns in this table = variables in ITem + variables in Face + Variables in
	// Moisturizer
	// (10 columns in total)
	// BODY OIL - A table that contains only Body oil product info. The columns in
	// this table = variables in ITem + variables in Body + Variables in Body oil
	// (10 columns in total)
	private static void createTables(SQLHelper productDbHelper) {
		// SQL query to create table if no such table exists. If a table of the given
		// name exists then nothing is done. ID is a primary key implying that the
		// values in the ID column are all unique and can be used to index the table.
		// NOT NULL implies that whenever a row is inserted into the table, it should
		// contain values for the the column specified as not null
		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS 	" + Constants.ALL_TABLE + "(\n" + "	" + Constants.ID
				+ " integer PRIMARY KEY, \n" + "	" + Constants.CATEGORY + " text NOT NULL, \n" + "	"
				+ Constants.NAME + " text NOT NULL, \n" + "	" + Constants.PRICE + " real, \n" + " "
				+ Constants.DESCRIPTION + " text, \n" + "	" + Constants.QUANTITY + " integer, \n" + " "
				+ Constants.INGREDIENTS + " text, \n" + "	" + Constants.SIZE + " integer \n" + ");";
		productDbHelper.createTable(sqlCreateTable);
		System.out.println("Table " + Constants.ALL_TABLE + " is created");

		sqlCreateTable = "CREATE TABLE IF NOT EXISTS 	" + Constants.DISCOUNT_PRODUCTS + "(\n"
				+ "	id integer PRIMARY KEY \n" + ");";
		productDbHelper.createTable(sqlCreateTable);

		sqlCreateTable = "CREATE TABLE IF NOT EXISTS 	" + Constants.SHAMPOO + "(\n" + "	" + Constants.ID
				+ " integer PRIMARY KEY, \n" + "	" + Constants.CATEGORY + " text NOT NULL, \n" + "	"
				+ Constants.NAME + " text NOT NULL, \n" + "	" + Constants.PRICE + " real, \n" + " "
				+ Constants.DESCRIPTION + " text, \n" + "	" + Constants.QUANTITY + " integer, \n" + " "
				+ Constants.INGREDIENTS + " text, \n" + "	" + Constants.SIZE + " integer, \n" + " "
				+ Constants.HAIR_TYPE + " text, \n" + " " + Constants.HAIR_GROWTH + " integer \n" + ");";
		productDbHelper.createTable(sqlCreateTable);
		System.out.println("Table " + Constants.SHAMPOO + " is created");

		sqlCreateTable = "CREATE TABLE IF NOT EXISTS 	" + Constants.MOISTURIZER + "(\n" + "	" + Constants.ID
				+ " integer PRIMARY KEY, \n" + "	" + Constants.CATEGORY + " text NOT NULL, \n" + " " + Constants.NAME
				+ " text NOT NULL, \n" + "	" + Constants.PRICE + " real, \n" + " " + Constants.DESCRIPTION
				+ " text, \n" + "	" + Constants.QUANTITY + " integer, \n" + " " + Constants.INGREDIENTS + " text, \n"
				+ "	" + Constants.SIZE + " integer, \n" + " " + Constants.SKIN_TYPE + " text, \n" + " "
				+ Constants.ACNE_PREVENTOR + " integer \n" + ");";
		productDbHelper.createTable(sqlCreateTable);
		System.out.println("Table " + Constants.MOISTURIZER + " is created");

		sqlCreateTable = "CREATE TABLE IF NOT EXISTS 	" + Constants.BODYOIL + "(\n" + "	" + Constants.ID
				+ " integer PRIMARY KEY, \n" + "	" + Constants.CATEGORY + " text NOT NULL, \n" + " " + Constants.NAME
				+ " text NOT NULL, \n" + "	" + Constants.PRICE + " real, \n" + " " + Constants.DESCRIPTION
				+ " text, \n" + "	" + Constants.QUANTITY + " integer, \n" + " " + Constants.INGREDIENTS + " text, \n"
				+ "	" + Constants.SIZE + " integer, \n" + " " + Constants.BODY_CONCERN + " text, \n" + " "
				+ Constants.FOR_DRY_SKIN + " integer \n" + ");";
		productDbHelper.createTable(sqlCreateTable);
		System.out.println("Table " + Constants.BODYOIL + " is created");
	}

	// Remember to add javadoc for public methods
	/**
	 * Static method to load product information from csv to SQL Database
	 * 
	 * @param fileName        The csv file that contains the product information
	 * @param productDbHelper The database helper object that facilitates the
	 *                        population of the SQL dB
	 * @param productList     Array list that contains all product information
	 * @param productLookUp   Hashmap that stores products base don their category
	 * @throws IOException                    - Exception thrown when the product
	 *                                        info csv file is missing
	 * @throws InvalidProductTypeException    - Exception thrown when the product
	 *                                        csv has an undefined product category
	 * @throws InsufficientAttributeException - Exception thrown when the product
	 *                                        csv has missing product information
	 */
	// Note: WE are not catching the exceptions thrown by the code inside this
	// function, hence we declare the thrown exceptions next to the function
	// definition. These exceptions have to be caught and handled by the code that
	// invokes the below function
	public static void processProductDatabase(String fileName, SQLHelper productDbHelper, ArrayList<Item> productList,
			ConcurrentHashMap<String, ArrayList<Item>> productLookUp)
			throws IOException, InvalidProductTypeException, InsufficientAttributeException {

		createTables(productDbHelper);

		// object output file name
		try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(Constants.OBJECT_OUT_FILE));) {
			String line = "";
			String delimiter = ","; // comma separates the objects
			try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
				while ((line = reader.readLine()) != null) {
					String[] entries = line.split(delimiter);
					int numOfEntries = entries.length;
					if (numOfEntries < Constants.MINIMUM_ATTRIBUTES_COUNT) {
						throw new InsufficientAttributeException("A minimum of " + Constants.MINIMUM_ATTRIBUTES_COUNT
								+ " attributes are rquired. However \" " + line + "\" has only " + numOfEntries
								+ " attributes");
					}
					int id = Integer.parseInt(entries[0]);
					String category = entries[1];
					String name = entries[2];
					double price = Double.parseDouble(entries[3]);
					String description = entries[4];
					int quantity = Integer.parseInt(entries[5]);
					String[] ingredients = entries[6].split(";");
					int size = Integer.parseInt(entries[7]);

					// insert to table
					// productDbHelper.insertToDb(Constants.ALL_TABLE, id, name, category, price);
					productDbHelper.insertToDb(Constants.ALL_TABLE, line);

					if (category.equals(Constants.SHAMPOO)) { // If the first word in the line is shampoo

						String hairType = entries[numOfEntries - 2];
						boolean hairGrowth = false; // assume hairgrowth is false
						if (entries[numOfEntries - 1].equals("Y")) { // unless a Y is indicated in the .csv file
							hairGrowth = true; // then assign to true
						}
						// then create a new shamoo object with these params
						Shampoo shampooObject = new Shampoo(id, category, name, price, description, ingredients, size,
								quantity, hairType, hairGrowth);
						productList.add(shampooObject);
						addItemToLookUp(productLookUp, Constants.SHAMPOO, shampooObject);
						objOut.writeObject(shampooObject);
						productDbHelper.insertToDb(Constants.DISCOUNT_PRODUCTS, id);
						// System.out.println(" Shampoo item was added with following info:");
						// System.out.println(shampooObject);
					} else if (category.equals(Constants.MOISTURIZER)) { // Or If the first word in the line is
						// moisturizer

						String skinType = entries[numOfEntries - 2];
						boolean acnePreventor = false;
						if (entries[numOfEntries - 1].equals("Y")) {

							acnePreventor = true;
						}
						// then create a new moisturizer object with these params
						Moisturizer moisturizerObject = new Moisturizer(id, category, name, price, description,
								ingredients, size, quantity, skinType, acnePreventor);
						productList.add(moisturizerObject);
						addItemToLookUp(productLookUp, Constants.MOISTURIZER, moisturizerObject);
						objOut.writeObject(moisturizerObject);
						// System.out.println("\n Moisturizer item was added with following info:");
						// System.out.println(moisturizerObject);

					} else if (category.equals(Constants.BODYOIL)) { // Or If the first word in the line is body oil

						String bodyConcern = entries[numOfEntries - 2];
						boolean forDrySkin = false;
						if (entries[numOfEntries - 1].equals("Y")) {
							forDrySkin = true;
						}
						// then create a new body oil object with these params
						BodyOil bodyoilObject = new BodyOil(id, category, name, price, description, ingredients, size,
								quantity, bodyConcern, forDrySkin);
						productList.add(bodyoilObject);
						addItemToLookUp(productLookUp, Constants.BODYOIL, bodyoilObject);
						objOut.writeObject(bodyoilObject);
						// System.out.println("\n Body Oil item was added with following info:");
						// System.out.println(bodyoilObject);
					} else {
						throw new InvalidProductTypeException(entries[0] + " is an invalid product type");
					}

					// test code
				}
			}
		}

	}

}
