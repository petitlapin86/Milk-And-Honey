package milk_and_honey;

/**
 * Body is a child of item and includes body specific methods
 *
 * @paige
 */

public abstract class Hair extends Item {

	String hairType;

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public Hair(double price, String decription, String name, String[] ingredients, Integer size, String hairType) {
		super(price, decription, name, ingredients, size);
		this.hairType = hairType;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString() + "\n hairType = " + hairType;
	}

}
