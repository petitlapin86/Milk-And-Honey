package milk_and_honey;

/**
 * Moisturizer is a child of Face and includes moisturizer specific methods
 *
 * @author Paige Jones
 */

public class Moisturizer extends Face {

	/**
	 * 
	 */
	private static final long serialVersionUID = 899532925513900343L;
	private boolean acnePreventor;

	public Moisturizer(int id, String category, String name, double price, String decription, String[] ingredients,
			int size, int quantity, String skinType, boolean acnePreventor) {
		super(id, category, price, decription, name, ingredients, size, quantity, skinType);
		this.acnePreventor = acnePreventor;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getIngredients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIngredients(String[] ingredients) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrice(int price) {
		// TODO Auto-generated method stub

	}

	// this adds on to the output within retailwebsite.java when the object is
	// moisturizer
	@Override
	public String toString() {
		return super.toString() + "\n Acne Preventor = " + acnePreventor;
	}

}
