/**
 * Purpose: SQL DATABASE CONNECTION 
 * @author Paige Jones
 */

package milk_and_honey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Purpose : handle SQLite database connections and query execution
 * 
 * @author paige
 *
 */
public class SQLHelper {

	// dbURL is the location of the SQLite database. The format of the url is as
	// follows:
	// dbc:sqlite:location_of_the_database
	private String dbUrl;

	public SQLHelper(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * Function to establish connection with SQLite Database.
	 * 
	 * @return Connection object that holds the DB connection status
	 */

	// BEfore executing any query or command it is necessary to first establish the
	// connection and then execute query or action
	public Connection connect() {
		Connection conn = null;
		try {

			// load driver
			// Class.forName("org.sqlite.JDBC");
			// if given database does not exists, then a new database is created

			conn = DriverManager.getConnection(dbUrl);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Function to execute the create table SQLite command
	 * 
	 * @param sqlCreateTable SQLite command to create a table
	 */
	public void createTable(String sqlCreateTable) {

		// USe the connect() function to establish the connection. The returned
		// connection value is later used to execute the SQLite command
		// Note that we are not performing conn.close() after executing the SQLite
		// statement because conn is created within the scope of the try block and will
		// closed automatically at the end of the try block
		// Statement is the SQLite class that is used to execute sql commands without
		// any parameters
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// execute create tbale sql statement
			stmt.execute(sqlCreateTable);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

//	public void insertToDb(String tableName, int id, String name, String category, double price) {
//		String sql_insert = "INSERT INTO " + tableName + "(id, name, category, price) VALUES(?,?,?,?) ";
//		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql_insert);) {
//			pstmt.setInt(1, id);
//			pstmt.setString(2, name);
//			pstmt.setString(3, category);
//			pstmt.setDouble(4, price);
//			pstmt.executeUpdate();
//			System.out.println(name + " is successsfully inserted");
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			ex.printStackTrace();
//		}
//	}

	/**
	 * Function to insert value into DB table with only ID value
	 * 
	 * @param tableName Table in the DB to which the given ID will be inserted
	 * @param id        ID of the product to be inserted
	 */
	public void insertToDb(String tableName, int id) {
		// Below sql query is parametarized. The ? in the VALUE section indicated that
		// the ID value will be fed later on.
		String sql_insert = "INSERT OR IGNORE INTO " + tableName + "(id) VALUES(?) ";
		// For parametarized SQL statements we will be using PrepaareStatement class of
		// SQLite. PrepareStatement can feed values to the replace ?
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql_insert);) {
			// the first input is the parameter index. It should be noted that parameter
			// index starts from 1. WE have only one parameter in the sql query as indicated
			// by the single questionmark.
			// the second input is the value for the corresponding parameter index
			pstmt.setInt(1, id);
			// once all parameters are given values, we will execute the query by invoking
			// the executeUpdate
			pstmt.executeUpdate();
			System.out.println(id + " is successsfully inserted");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Function to insert a csv line to Table
	 * 
	 * @param tableName Table to which the csv line will be inserted
	 * @param line      csv line to be inserted
	 */
	// Note that we have two functions named insertDB with two different sets of
	// inputs. This mechanism is called function overloading. THis should not be
	// confused with Polymorphism. Polymorphism happens only during inheritance
	// across parent class and subclass. Function overloading happens in the same
	// class. In polymorphism everything must be the same: name and input set. In
	// function overloading only the function names are the same, the input sets are
	// different
	public void insertToDb(String tableName, String line) {
		// We will not only be inserting to ALL_TABLE but also to one of the Shampoo,
		// Moisturizer of Body OIl tables base don the category specified in the given
		// line
		// INSERT or IGNORE. THis implies Insert into table if an item with given ID is
		// not present in the table else ignore and do nothing
		// all_db has 8 parameters
		String sql_insert_all_db = "INSERT OR IGNORE INTO " + tableName + "("
				+ String.join(",", Constants.ID, Constants.CATEGORY, Constants.NAME, Constants.PRICE,
						Constants.DESCRIPTION, Constants.QUANTITY, Constants.INGREDIENTS, Constants.SIZE)
				+ ") VALUES(?,?,?,?,?,?,?,?) ";
		// the rest of them have 10 parameters. count the number question marks in the
		// VALUES
		String sql_insert_shampoo_db = "INSERT OR IGNORE INTO " + Constants.SHAMPOO + "("
				+ String.join(",", Constants.ID, Constants.CATEGORY, Constants.NAME, Constants.PRICE,
						Constants.DESCRIPTION, Constants.QUANTITY, Constants.INGREDIENTS, Constants.SIZE,
						Constants.HAIR_TYPE, Constants.HAIR_GROWTH)
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?) ";
		String sql_insert_moisturizer_db = "INSERT OR IGNORE INTO " + Constants.MOISTURIZER + "("
				+ String.join(",", Constants.ID, Constants.CATEGORY, Constants.NAME, Constants.PRICE,
						Constants.DESCRIPTION, Constants.QUANTITY, Constants.INGREDIENTS, Constants.SIZE,
						Constants.SKIN_TYPE, Constants.ACNE_PREVENTOR)
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?) ";
		String sql_insert_bodyoil_db = "INSERT OR IGNORE INTO " + Constants.BODYOIL + "("
				+ String.join(",", Constants.ID, Constants.CATEGORY, Constants.NAME, Constants.PRICE,
						Constants.DESCRIPTION, Constants.QUANTITY, Constants.INGREDIENTS, Constants.SIZE,
						Constants.BODY_CONCERN, Constants.FOR_DRY_SKIN)
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?) ";
		try (Connection conn = this.connect();
				// Since the query is parametarized we will be using PrepareStatement
				PreparedStatement pstmt = conn.prepareStatement(sql_insert_all_db);
				PreparedStatement pstmtShampoo = conn.prepareStatement(sql_insert_shampoo_db);
				PreparedStatement pstmtMoisturizer = conn.prepareStatement(sql_insert_moisturizer_db);
				PreparedStatement pstmtBodyOil = conn.prepareStatement(sql_insert_bodyoil_db)) {
			String[] entries = line.split(Constants.CSV_SEPARATOR);
			// the last two entries in every line is product specific variables, the rest
			// all common across all items
			int numOfItemFieldEntries = entries.length - 2;

			// Assign corresponding entry value to the parameter index. Note that parameter
			// index starts from 1
			for (int i = 0; i < numOfItemFieldEntries; i++) {
				// System.out.println(entries[i]);
				pstmt.setObject(i + 1, entries[i]);
				pstmtShampoo.setObject(i + 1, entries[i]);
				pstmtMoisturizer.setObject(i + 1, entries[i]);
				pstmtBodyOil.setObject(i + 1, entries[i]);
				// the prepareStatement query is built for all_db, shampoo, moisturizer and body
				// oil tables. all_db statement will be executed. Out of the remaining3 only one
				// of the statements will be executed based on the category specified in the
				// line.
			}
			int i = numOfItemFieldEntries;
			// execute update for all_db
			pstmt.executeUpdate();
			// check category, based on which the last two entries from the given line is
			// read and the corresponding prepare statement is executed.
			String category = entries[Constants.CATEGORY_INDEX];
			if (category.equals(Constants.SHAMPOO)) {
				pstmtShampoo.setObject(i + 1, entries[i++]);
				pstmtShampoo.setObject(i + 1, entries[i++]);

				pstmtShampoo.executeUpdate();
			} else if (category.equals(Constants.MOISTURIZER)) {
				pstmtMoisturizer.setObject(i + 1, entries[i++]);
				pstmtMoisturizer.setObject(i + 1, entries[i++]);

				pstmtMoisturizer.executeUpdate();
			} else if (category.equals(Constants.BODYOIL)) {
				pstmtBodyOil.setObject(i + 1, entries[i++]);
				pstmtBodyOil.setObject(i + 1, entries[i++]);

				pstmtBodyOil.executeUpdate();

			} else {
				throw new InvalidProductTypeException(entries[0] + " is an invalid product type");
			}
			System.out.println(entries[2]);
			System.out.println(entries[2] + " is successsfully inserted");
		} catch (SQLException | InvalidProductTypeException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Perform Join with the given two tables and display the discounted product
	 * details
	 * 
	 * @param discountTable   SQLite table that contains the ids of discounted items
	 * @param allProductTable SQLite table that contains info of all items
	 */
	public void displayAllDiscountedProducts(String discountTable, String allProductTable) {

		// sql statement for inner join
		String sql_select = "SELECT a.id, a.name, a.category, a.price FROM " + allProductTable + " a INNER JOIN "
				+ discountTable + " d ON a.id=d.id ";
		// System.out.println(sql_select);
		ResultSet res = null;

		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// the output of executeQuesry is a ResultSet. You access the result set
			// elements by repeatedly invoking resultset.next()
			res = stmt.executeQuery(sql_select);
			int columnCount = res.getMetaData().getColumnCount();

			while (res.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.println(" " + res.getMetaData().getColumnLabel(i) + " = " + res.getObject(i));
				}

				System.out.println();
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

	/**
	 * Function to return items in increasing order of price
	 * 
	 * @param tableName SQLite table from which items will be retrieved
	 * @return list of items arranged in increasing order of price
	 */
	public ArrayList<Item> priceLowToHigh(String tableName) {
		String sql_select = "SELECT * FROM " + tableName + " ORDER BY price ASC";
		ResultSet res = null;
		ArrayList<Item> result = new ArrayList<Item>();
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			res = stmt.executeQuery(sql_select);

			while (res.next()) {
				Item currentResult = convertDbResToItem(res);
				result.add(currentResult);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		return result;

	}

	/**
	 * Function get all rows from the given table
	 * 
	 * @param tableName SQLite table from which items are retrieved
	 * @return list of Items stored in the given table in row form
	 */
	public ArrayList<Item> getAllProductList(String tableName) {
		String sql_select = "SELECT * FROM " + tableName;
		ResultSet res = null;
		ArrayList<Item> result = new ArrayList<Item>();
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			res = stmt.executeQuery(sql_select);

			while (res.next()) {
				Item currentResult = convertDbResToItem(res);
				result.add(currentResult);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		return result;

	}

	// Function to map the resultset rows with the corresponding classes. Each
	// resultset row has column values that map to a variable in the Item or one of
	// its subclasses. For each row a corresponding Shampoo, Moisturizer or BodyOIl
	// object is created and the column values a re mapped to the variables. THese
	// objects are then stored in an arraylist
	private Item convertDbResToItem(ResultSet res) {
		try {
			int id = res.getInt(Constants.ID);
			String category = res.getString(Constants.CATEGORY);
			String name = res.getString(Constants.NAME);
			double price = res.getDouble(Constants.PRICE);
			String description = res.getString(Constants.DESCRIPTION);
			int quantity = res.getInt(Constants.QUANTITY);
			String[] ingredients = res.getString(Constants.INGREDIENTS).split(Constants.INGREDIENT_SEPARATOR);
			int size = res.getInt(Constants.SIZE);
			if (category.equals(Constants.SHAMPOO)) {
				String sql_select = "SELECT * FROM " + Constants.SHAMPOO + " WHERE " + Constants.ID + "= ?";
				ResultSet resShampoo = null;

				try (Connection conn = this.connect(); PreparedStatement stmt = conn.prepareStatement(sql_select);) {
					stmt.setInt(1, id);
					resShampoo = stmt.executeQuery();
					while (resShampoo.next()) {
						String hairType = resShampoo.getString(Constants.HAIR_TYPE);
						boolean hairGrowth = resShampoo.getBoolean(Constants.HAIR_GROWTH);
						return new Shampoo(id, category, name, price, description, ingredients, size, quantity,
								hairType, hairGrowth);
					}
				}

			} else if (category.equals(Constants.MOISTURIZER)) {
				String sql_select = "SELECT * FROM " + Constants.MOISTURIZER + " WHERE " + Constants.ID + "= ?";
				ResultSet resShampoo = null;

				try (Connection conn = this.connect(); PreparedStatement stmt = conn.prepareStatement(sql_select);) {
					stmt.setInt(1, id);
					resShampoo = stmt.executeQuery();
					while (resShampoo.next()) {
						String skinType = resShampoo.getString(Constants.SKIN_TYPE);
						boolean acnePreventor = resShampoo.getBoolean(Constants.ACNE_PREVENTOR);
						return new Moisturizer(id, category, name, price, description, ingredients, size, quantity,
								skinType, acnePreventor);
					}
				}

			} else if (category.equals(Constants.BODYOIL)) {
				String sql_select = "SELECT * FROM " + Constants.BODYOIL + " WHERE " + Constants.ID + "= ?";
				ResultSet resShampoo = null;

				try (Connection conn = this.connect(); PreparedStatement stmt = conn.prepareStatement(sql_select);) {
					stmt.setInt(1, id);
					resShampoo = stmt.executeQuery();
					while (resShampoo.next()) {
						String bodyConcern = resShampoo.getString(Constants.BODY_CONCERN);
						boolean forDrySkin = resShampoo.getBoolean(Constants.FOR_DRY_SKIN);
						return new BodyOil(id, category, name, price, description, ingredients, size, quantity,
								bodyConcern, forDrySkin);
					}
				}

			} else {
				throw new InvalidProductTypeException(category + " is an invalid product type");

			}

		} catch (SQLException | InvalidProductTypeException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		return null;
	}

	// public void dropTable(String tableName) {
	//
	// // Shampoo,Lavender Shampoo,10.00,A soothing shampoo for dry
	// // hair,500,4,Lavender,Hemp seed,Jojoba oil,Coconut oil,200,dry,Y
	// // SQL statement for creating table
	// String sql_drop_table = "DROP TABLE " + tableName;
	//
	// try (Connection conn = this.connect(); Statement stmt =
	// conn.createStatement();) {
	// // execute create tbale sql statement
	// stmt.execute(sql_drop_table);
	// System.out.println("Tbale " + tableName + " is created");
	// } catch (SQLException ex) {
	// System.out.println(ex.getMessage());
	// ex.printStackTrace();
	// }
	// }

}
