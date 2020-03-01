package milk_and_honey;

/**
 * Class to contain milk and honey project constants
 * 
 * @author paige
 *
 */
public class Constants {

	public static final String SHAMPOO = "Shampoo";
	public static final String MOISTURIZER = "Moisturizer";
	public static final String BODYOIL = "BodyOil";
	public static final int MINIMUM_ATTRIBUTES_COUNT = 8;
	public static final int LIMITED_AVAILABILTY_QUANTIY = 15;
	public static final String OBJECT_OUT_FILE = "./src/database/myproducts.txt";

	// DB table ames
	public static final String DB_URL = "jdbc:sqlite:sql_database/products.db";
	public static final String ALL_TABLE = "all_products";
	public static final String DISCOUNT_PRODUCTS = "discount_products";

	public static final String CSV_SEPARATOR = ",";
	public static final String INGREDIENT_SEPARATOR = ";";

	// DB column names
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String QUANTITY = "quantity";
	public static final String INGREDIENTS = "ingredients";
	public static final String SIZE = "size";
	public static final String HAIR_TYPE = "hairType";
	public static final String HAIR_GROWTH = "hairGrowth";
	public static final String SKIN_TYPE = "skinType";
	public static final String ACNE_PREVENTOR = "acnePreventor";
	public static final String BODY_CONCERN = "bodyCOncern";
	public static final String FOR_DRY_SKIN = "forDrySkin";
	public static final int CATEGORY_INDEX = 1;

}
