package milk_and_honey;

/**
 * Item is a super class that stubs out new products body, face and hair all
 * inherit from this class as well as their children: shampoo, body oil and
 * moisturizer
 *
 * @author Paige Jones
 */

public abstract class Item { // super class

	protected double price;

	protected String description;

	protected String name;

	protected String[] ingredients;

	protected int size;

	public abstract void display();

	public abstract String getType();

	public abstract double getPrice();

	public abstract String getDescription();

	public abstract String[] getIngredients();

	public abstract int getSize(); // ml or grams based on product

	public abstract void setIngredients(String[] ingredients);

	public abstract void setSize(int size);

	public abstract void setPrice(int price);

	// constructor for new products
	public Item(double price, String decription, String name, String[] ingredients, Integer size) {
		this.price = price;
		this.description = description;
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(" Name = " + name + "\n Price = " + price + "\n Description = " + description);
		str.append("\n Ingredients = ");
		for (int i = 0; i < ingredients.length; i++) {
			str.append(ingredients[i]);
			if (i < ingredients.length - 1) // append comma except for the last ingredient
			{
				str.append(",");
			}
		}
		str.append("\n Size = " + size);
		return str.toString();

	}

}
