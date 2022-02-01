package application.view;

import java.io.IOException;

import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddNoteController implements EventHandler <ActionEvent> {
	@FXML
	private Label title;
	
	@FXML
	private Label saveAlert;
	
	@FXML
	private TextField chap;
	
	@FXML
	private TextField startVerse;
	
	@FXML 
	private TextField endVerse;
	
	@FXML
	private TextArea newNoteText;
	
	public String nameOfBook;
		
	@FXML
	void initialize(){
		nameOfBook = BooksViewController.bookName;
		title.setText(nameOfBook);
    }
	
	@FXML
	public void save(ActionEvent event) {
		if(chap.getText().equals("") || startVerse.getText().equals("") || endVerse.getText().equals("") || newNoteText.getText().equals("") || Book.isNumeric(chap.getText()) == false || Book.isNumeric(startVerse.getText()) == false || Book.isNumeric(endVerse.getText()) == false) {
			saveAlert.setText("Could not save - Try Again");
			return;
		}
		int i = 0;
		try {
			i = Book.addNote("data/Notes.txt", nameOfBook, chap.getText(), startVerse.getText(), endVerse.getText(), newNoteText.getText());
			if(i == 0) {
				saveAlert.setText("Note Saved!");
				Main.showNotesByBookView();
			} else {
				saveAlert.setText("Could not save - Note Already Created");
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			Main.showNotesByBookView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
