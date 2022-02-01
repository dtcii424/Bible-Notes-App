package application.view;

import java.io.IOException;
import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

public class SearchViewController implements EventHandler <ActionEvent>{
	@FXML
	private TextField searchbar;
	
	@FXML
	private Label errorL;
	
	public void initialize(){
		if(!Book.notesInBook.contains("No Notes Found")) {
			TextFields.bindAutoCompletion(searchbar, Book.notesInBook);
		}
    }
	
	@FXML
	public void select(ActionEvent event) {
		AllNotesViewController.note = searchbar.getText();
		if(Book.notesInBook.contains(AllNotesViewController.note)) {
			AllNotesViewController.noteText = Book.getNote("data/Notes.txt", AllNotesViewController.note);
			try {
				Main.newStage.close();
				Main.showViewNoteFromAllView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			errorL.setText("Note Not Found");
			return;
		}
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		Main.newStage.close();
	}

}
