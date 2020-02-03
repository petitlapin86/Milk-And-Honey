package milk_and_honey;

/**
 * Face is a child of item and includes face specific methods
 *
 * @author Paige Jones
 */
public abstract class Face extends Item {

	String skinType;

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public Face(double price, String decription, String name, String[] ingredients, Integer size, String skinType) {
		super(price, decription, name, ingredients, size);
		this.skinType = skinType;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString() + "\n skinType = " + skinType;
	}

}
