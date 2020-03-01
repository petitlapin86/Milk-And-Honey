package milk_and_honey;

/**
 * Face is a child of item and includes face specific methods
 *
 * @author Paige Jones
 */
public abstract class Face extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8134502411581520220L;
	String skinType;

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public Face(int id, String category, double price, String decription, String name, String[] ingredients, int size,
			int quantity, String skinType) {
		super(id, category, price, decription, name, ingredients, size, quantity);
		this.skinType = skinType;
		// TODO Auto-generated constructor stub
	}

	// this is for output relating to retailwebsite.java
	@Override
	public String toString() {
		return super.toString() + "\n skinType = " + skinType;
	}

}
