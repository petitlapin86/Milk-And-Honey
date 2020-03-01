package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	@FXML
	private TextField firstName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("View is now loaded!");
	}

	// respond to user name input
	public void handleName(ActionEvent actionEvent) {
		System.out.printf("This input name has been recieved!", firstName.getText());
		// TODO print to window instead of console
	}

	// close app
	public void exitApp(ActionEvent actionEvent) {
		System.out.println("Goodbye!");
		System.exit(0);
	}

}
