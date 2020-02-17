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