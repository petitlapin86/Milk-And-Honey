package milk_and_honey;

/**
 * User defined exception that is thrown when the product csv file has
 * insufficient data. For instance, missing information for Lavendar Shampoo
 * 
 * @Author Paige Jones
 */
public class InsufficientAttributeException extends Exception {

	private static final long serialVersionUID = 2864425056826921426L;

	public InsufficientAttributeException(String s) {
		super(s);
	}

}
