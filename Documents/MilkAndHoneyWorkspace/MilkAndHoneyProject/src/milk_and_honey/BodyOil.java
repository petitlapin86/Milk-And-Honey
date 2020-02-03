package milk_and_honey;

/**
 * Body Oil is a child of Body and includes bodyoil specific methods
 *
 * @author Paige Jones
 */
public class BodyOil extends Body { // inherits from body which inherits from super class item

	public BodyOil(double price, String decription, String name, String[] ingredients, Integer size,
			String description) {
		super(price, decription, name, ingredients, size, description);
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

}
