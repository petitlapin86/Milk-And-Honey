package milk_and_honey;

/**
 * Body is a child of item and includes body specific methods
 *
 * @author paige
 */

public abstract class Body extends Item { // body inherits from super class item

	String bodyConcern;

	public String getBodyConcern() {
		return bodyConcern;// define methods that are specific to body class
	}

	public void setBodyConcern(String bodyConcern) {
		this.bodyConcern = bodyConcern;
	}

	public Body(double price, String decription, String name, String[] ingredients, Integer size, String bodyConcern) {
		super(price, decription, name, ingredients, size);
		this.bodyConcern = bodyConcern;
		// TODO Auto-generated constructor stub
	}

}
