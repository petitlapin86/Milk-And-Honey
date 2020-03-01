package milk_and_honey;

/**
 * Shampoo is a child of hair and includes shampoo specific methods
 *
 * @author Paige Jones
 */

public class Shampoo extends Hair {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656119571319214291L;
	private boolean hairGrowth = false;

	public Shampoo(int id, String category, String name, double price, String decription, String[] ingredients,
			int size, int quantity, String hairType, boolean hairGrowth) {
		super(id, category, price, decription, name, ingredients, size, quantity, hairType);
		this.hairGrowth = hairGrowth;
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

	public boolean thickeningShampoo() {
		// TODO Auto-generated method stub
		return hairGrowth;
	}

	// this adds on to the output within retailwebsite.java when the object is
	// shampoo
	@Override
	public String toString() {
		return super.toString() + "\n Thickens = " + hairGrowth;
	}

}
