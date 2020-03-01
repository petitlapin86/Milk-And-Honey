PAIGE MADELINE JONES BU MET CS 622 

To RUN:
Navigate to RetailWebsite.java (where my main class is located) 
Run the program:
• if using eclipse just press the green play button
• if using the command line compile by typing javac RetailWebsite.java and then run by typing java RetailWebsite


The project is entitled MILK & HONEY

It is a program that (when complete) will for browsing, selecting, adding, removing skin-care products to a cart and proceeding with checkout.

CHANGES THIS WEEK 2:

REFINE
• successfully linked project to github
• fixed errors within the retailwebsite.java from last week 
• update readme.md to include run instructions
• add code formatting
• began working on cart functionality

IMPLEMENTATION
• added a database for products and created a class that reads the file and assigns variables to each object. 
• output the products to the console including child specific variations (for example is this face cream an acne preventer? T/F). 
• added a scanner for user input of name and program introduction. 
• implemented first JUNIT test (RetailWebsiteTest.java)
• implemented Exceptions: insufficientAttributException, invalidProductTypeException and ProductDatabaseFileException

CHANGES THIS WEEK 3:

REFINE
• implement generics to cart.java - if we want to get items from the users cart of a specific type (next week I will add a discount so this functionality will become more useful)
• implement cart functions to add, get total and display
• moved main function to the driver (I think this will be cleaner once I integrate a front-end)\
• refined retailwebsite.java 
• refined test retailwebsitetest.java  

IMPLEMENTATION
• added an output main menu startShopping() which utilizes a while true and switch statement
• added an output submenu to add to cart displayItemsOfGivenType() which utilizes a while true, for loop and switch statement

CHANGES THIS WEEK 4:

IMPLEMENTATION
• created a new project using javafx
• created a fxml file, a controller.java, a main.java, a resources folder with images and css files. 
• spent a LOT of time trying to learn javafx and feel I made good progress with the buttons, and text input fields - I currently having it responding to certain button clicks via the console and am hopeful that I will be able to import my existing code and begin really linking up the "model" functionality. 
• added input read/write read from csv and dump into an object file, keep updating inventory and write unavailable objects to file with updateInventory() 
• add streams displayLimitedAvailablItems() which shows the user items that have inventory of 15 or less. 
• complete requirement to display users cart with items. 


TO RUN IN WEEK 4
•To run the original MilkAndHoneyProject navigate to src > milk_and_honey > driver.java and hit run. 
•To launch the MilkAndHoneyjavaFX version navigate src > application > Main.java and hit run.   


FUTURE TODO: 
• validate user input
• precondition/post condition and overall better header comments
• continue on project requirements
• connect model logic to javafx interface
• refine retailwebsite.java to more files (less features all in one place)

CHANGES THIS WEEK 5:

IMPLEMENTATION
• implement SQLlite 
• create SQlhelper class 
• Use SQL to create table
• Use SQL to order products by price productsHighToLow()
• Use SQL to join tables and show all discounted products displayAllDiscountedProducts()
• add Id to each product in processProductDatabase class() 

CHANGES THIS WEEK 6:
• implement concurrency - use threads to allow multiple users to access our website
• refactored retailwebsite.java
• fixed the db error on inserting duplicated
• update heading comments throughout 

TO RUN IN WEEK 6
•To run the original MilkAndHoneyProject navigate to src > milk_and_honey > driver.java and hit run. 
•To launch the MilkAndHoneyjavaFX version navigate src > application > Main.java and hit run.  


