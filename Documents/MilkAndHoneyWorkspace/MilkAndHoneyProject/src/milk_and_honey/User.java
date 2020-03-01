package milk_and_honey;

import java.util.Scanner;

public class User extends Thread {

	public void run() {
		Scanner input = new Scanner(System.in);
		// Ask User for name
		System.out.print("Welcome! What is your name?: ");
		String myString = input.next();
		System.out.println("Hey " + myString + ", Thanks for stopping by the Milk & Honey Store. \n");

		// Print Introduction to console
		System.out.println("All of our products are created by simple, whole ingredients found in nature. "
				+ "\nIn an effort to lower our carbon footprint we work with farms located within "
				+ "\nthe state of Vermont (where you'll find us!)  \n");

		// Ask User if they would like to enter the store
		System.out.print("\n Would you like to Take a look around the Milk & Honey Store? Y/N: ");
		String answer = input.next();
		if (answer.equals("Y") || answer.equals("y")) {
			System.out.println(" Okay great! Lets go shopping \n");
			RetailWebsite myWebsite = new RetailWebsite();
			myWebsite.startShopping();
		} else
			System.out.println(" Thanks for browsing, goodbye. \n");
	}

}
