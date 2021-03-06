package milk_and_honey;

/**
 * Body is a child of item and includes body specific methods
 *
 * @author paige
 */

public abstract class Body extends Item { // body inherits from super class item

	private static final long serialVersionUID = -4040303259804983252L;
	String bodyConcern;

	public String getBodyConcern() {
		return bodyConcern;// define methods that are specific to body class
	}

	public void setBodyConcern(String bodyConcern) {
		this.bodyConcern = bodyConcern;
	}

	public Body(int id, String category, double price, String decription, String name, String[] ingredients, int size,
			int quantity, String bodyConcern) {
		super(id, category, price, decription, name, ingredients, size, quantity);
		this.bodyConcern = bodyConcern;
		// TODO Auto-generated constructor stub
	}

	// this is for output relating to retailwebsite.java
	@Override
	public String toString() {
		return super.toString() + "\n Body Concern = " + bodyConcern;
	}

}
