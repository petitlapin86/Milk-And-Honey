package milk_and_honey;

/**
 * User defined exception that is thrown when the product csv file contains
 * products other than Shampoo, Moisturizer and Body oil
 * 
 * @author Paige Jones
 */

public class InvalidProductTypeException extends Exception {

	private static final long serialVersionUID = -1476793739933120592L;

	public InvalidProductTypeException(String s) {
		super(s);
	}

}
