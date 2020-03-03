package milk_and_honey;

import java.io.Serializable;

/**
 * Item is a super class that stubs out new products body, face and hair all
 * inherit from this class as well as their children: shampoo, body oil and
 * moisturizer.
 *
 * @author Paige Jones
 */

//This is an abstract class as indicated by the abstract keyword.
//It basically lists out all variables that is common to Hair, face and body products

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

	// This function also implements polymorphism
	public String getType() {
		return "Item";
	}

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

	// THis functiuon implements polymorphism. Polymorphism is a process that is
	// related to Inheritance.
	// Polymorphism => "one name but many forms"
	// This implies that the children and the parent all have functions with the
	// same but each of them defines their own functionality
	// toString function implements polymorphism because Item, Body, Hair and Face
	// all have toString but their behaviour
	// varies based on the object type that invoke the toString function.
	// For example
	// Item bodyObj = new Body();
	// bodyObj.toString(); will invoke the toString function of the Body class
	// bodyObj belongs to Body class is resolved dynamically at run time
	// Polymprphism is also associated with @Override tag. Whenever you redefine the
	// functionality of a function defined in the parent class
	// use the @Override keyword, indicating polymorphism
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
		return category;
	}

}
