package milk_and_honey;

public class Shampoo extends Hair{

	private boolean hairGrowth= false;
	
	public Shampoo(String name, double price, String decription, String[] ingredients, Integer size,
			String hairType, boolean hairGrowth) {
		super(price, decription, name, ingredients, size, hairType);
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

	

	
	@Override
	public String toString()
	{
		return super.toString() + "\n Thickens = " + hairGrowth;
	}

}
