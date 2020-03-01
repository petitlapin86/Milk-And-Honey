package milk_and_honey;

import java.io.Serializable;

/**
 * Item is a super class that stubs out new products body, face and hair all
 * inherit from this class as well as their children: shampoo, body oil and
 * moisturizer
 *
 * @author Paige Jones
 */

public abstract class Item implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7504989085447975048L;

// super class
	protected int id;

	protected String category;

	protected double price;

	protected String description;

	protected String name;

	protected String[] ingredients;

	protected int size;

	protected int quantity;

	public abstract void display();

	public abstract String getType();

	// Returns the price of the item
	public double getPrice() {
		return price;
	};

	// Returns the name of the item
	public String getName() {
		return name;
	};

	// Returns the quantity of the item
	public int getQuantity() {
		return quantity;
	}

	public abstract String getDescription();

	public abstract String[] getIngredients();

	public abstract int getSize(); // ml or grams based on product

	public abstract void setIngredients(String[] ingredients);

	public abstract void setSize(int size);

	public abstract void setPrice(int price);

	public int getQuantiy() {
		return quantity;
	}

	// constructor for new products
	public Item(int id, String category, double price, String description, String name, String[] ingredients, int size,
			int quantity) {
		this.price = price;
		this.description = description;
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.quantity = quantity;
		this.id = id;
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(" Name = " + name + "\n Price = $" + price + "\n Description = " + description);
		str.append("\n Ingredients = ");
		for (int i = 0; i < ingredients.length; i++) {
			str.append(ingredients[i]);
			if (i < ingredients.length - 1) // append comma except for the last ingredient
			{
				str.append(",");
			}
		}
		str.append("\n Size = " + size + "ml");
		str.append("\n Quantity = " + quantity);
		return str.toString();

	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

}
