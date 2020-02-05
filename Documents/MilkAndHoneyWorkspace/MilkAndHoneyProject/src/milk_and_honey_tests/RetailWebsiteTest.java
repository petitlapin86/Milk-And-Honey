package milk_and_honey_tests;

//import the class thats being tested
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import milk_and_honey.InsufficientAttributeException;
import milk_and_honey.InvalidProductTypeException;
import milk_and_honey.RetailWebsite;

/*
 * @author Paige Jones 
 * 
 * notes for myself about writing tests:
 * 1. I am using JUnit5
 * 2. Clicking run on a junit test opens up the JUnit console on the left. This shows the progression of tests and their success/failure
 * 3. Success = green and failure = red
 */

class RetailWebsiteTest {

	@Test // every Junit test is annotated with @Test tag.
	void testInvalidFilePath() { // This is the unit test name
		String filename = "invalid.csv"; // invalid file name
		Assertions.assertThrows(IOException.class, () -> {
			RetailWebsite.processProductDatabase(filename);
		}); // assertThrows will check if the invoked method throws the exception specified
			// as the first parameter. Here I am checking whether processProductDatabase is
			// throwing IOException
	}

	@Test
	void testInvalidProductType() {
		String filename = "./src/test_database/invalid_product_type.csv"; // file with invalid product
		Assertions.assertThrows(InvalidProductTypeException.class, () -> {
			RetailWebsite.processProductDatabase(filename);
		}); // assertThrows will check if the invoked method throws the exception specified
			// as the first parameter. Here I am checking whether processProductDatabase is
			// throwing InvalidProductTypeException
	}

	@Test
	void testInsufficientAttributes() {
		String filename = "./src/test_database/insuff_attri.csv"; // file with invalid product
		Assertions.assertThrows(InsufficientAttributeException.class, () -> {
			RetailWebsite.processProductDatabase(filename);
		}); // assertThrows will check if the invoked method throws the exception specified
			// as the first parameter. Here I am checking whether processProductDatabase is
			// throwing InsufficientAttributeException
	}

}
