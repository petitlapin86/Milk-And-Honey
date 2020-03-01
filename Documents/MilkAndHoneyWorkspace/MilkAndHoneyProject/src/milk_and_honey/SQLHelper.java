package milk_and_honey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//class to handle SQLite database connections
public class SQLHelper {

	String dbUrl;

	public SQLHelper(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	// function to check database connection
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

	// To-do Move this to onCreate when creating a Java application
	public void createTable(String sqlCreateTable) {
		// Shampoo,Lavender Shampoo,10.00,A soothing shampoo for dry
		// hair,500,4,Lavender,Hemp seed,Jojoba oil,Coconut oil,200,dry,Y
		// SQL statement for creating table

		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// execute create tbale sql statement
			stmt.execute(sqlCreateTable);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	// To-do move this to onCreate
	// To-do read from csv and send strings here for insertion
	public void insertToDb(String tableName, int id, String name, String category, double price) {
		String sql_insert = "INSERT INTO " + tableName + "(id, name, category, price) VALUES(?,?,?,?) ";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql_insert);) {
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, category);
			pstmt.setDouble(4, price);
			pstmt.executeUpdate();
			System.out.println(name + " is successsfully inserted");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	// To-do move this to onCreate
	// To-do read from csv and send strings here for insertion
	public void insertToDb(String tableName, int id) {
		String sql_insert = "INSERT OR IGNORE INTO " + tableName + "(id) VALUES(?) ";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql_insert);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println(id + " is successsfully inserted");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void insertToDb(String tableName, String line) {

		String sql_insert_all_db = "INSERT OR IGNORE INTO " + tableName + "("
				+ String.join(",", Constants.ID, Constants.CATEGORY, Constants.NAME, Constants.PRICE,
						Constants.DESCRIPTION, Constants.QUANTITY, Constants.INGREDIENTS, Constants.SIZE)
				+ ") VALUES(?,?,?,?,?,?,?,?) ";
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
				PreparedStatement pstmt = conn.prepareStatement(sql_insert_all_db);
				PreparedStatement pstmtShampoo = conn.prepareStatement(sql_insert_shampoo_db);
				PreparedStatement pstmtMoisturizer = conn.prepareStatement(sql_insert_moisturizer_db);
				PreparedStatement pstmtBodyOil = conn.prepareStatement(sql_insert_bodyoil_db)) {
			String[] entries = line.split(Constants.CSV_SEPARATOR);
			int numOfItemFieldEntries = entries.length - 2;

			for (int i = 0; i < numOfItemFieldEntries; i++) {
				// System.out.println(entries[i]);
				pstmt.setObject(i + 1, entries[i]);
				pstmtShampoo.setObject(i + 1, entries[i]);
				pstmtMoisturizer.setObject(i + 1, entries[i]);
				pstmtBodyOil.setObject(i + 1, entries[i]);
			}
			int i = numOfItemFieldEntries;
			pstmt.executeUpdate();
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

	public void displayAllDiscountedProducts(String discountTable, String allProductTable) {
		String sql_select = "SELECT a.id, a.name, a.category, a.price FROM " + allProductTable + " a INNER JOIN "
				+ discountTable + " d ON a.id=d.id ";
		// System.out.println(sql_select);
		ResultSet res = null;

		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
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

	public void dropTable(String tableName) {

		// Shampoo,Lavender Shampoo,10.00,A soothing shampoo for dry
		// hair,500,4,Lavender,Hemp seed,Jojoba oil,Coconut oil,200,dry,Y
		// SQL statement for creating table
		String sql_drop_table = "DROP TABLE " + tableName;

		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// execute create tbale sql statement
			stmt.execute(sql_drop_table);
			System.out.println("Tbale " + tableName + " is created");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
