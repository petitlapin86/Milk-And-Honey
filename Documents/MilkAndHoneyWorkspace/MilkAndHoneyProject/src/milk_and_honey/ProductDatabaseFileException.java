package milk_and_honey;

/**
 * User defined exception that is thrown when there is some inconsistency in the
 * product csv file
 * 
 * @author Paige Jones
 */
public class ProductDatabaseFileException extends Exception {

	private static final long serialVersionUID = -1547475041729593721L;

	public ProductDatabaseFileException(String s) {
		super(s);
	}

}
