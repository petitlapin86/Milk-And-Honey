package milk_and_honey;

/**
 * Hair is a child of item and includes hair specific methods
 *
 * @author Paige Jones
 */

public abstract class Hair extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1977879108143509188L;
	String hairType;

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public Hair(int id, String category, double price, String decription, String name, String[] ingredients, int size,
			int quantity, String hairType) {
		super(id, category, price, decription, name, ingredients, size, quantity);
		this.hairType = hairType;
		// TODO Auto-generated constructor stub
	}

	// this is for output relating to retailwebsite.java
	@Override
	public String toString() {
		return super.toString() + "\n hairType = " + hairType;
	}

}
