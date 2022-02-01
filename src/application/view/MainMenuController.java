package application.view;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController implements EventHandler <ActionEvent> {
	@FXML
	private Button books;
	
	@FXML
	private Button notes;

	@Override
	public void handle(ActionEvent event) {
		// books
		try {
			Main.showBooksView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void allNotes(ActionEvent event) {
		try {
			Main.showAllNotesView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
