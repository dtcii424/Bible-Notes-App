package application.view;

import java.io.IOException;

import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class NotesByBookController implements EventHandler <ActionEvent> {
	@FXML
	private Label title;
	
	@FXML
	private Button select;
	
	@FXML
	private Button add;
	
	@FXML
	private ListView<String> listOfNotes;
	
	public static String note;
	
	public static String noteText;
	
	public static int i;
	
	@FXML
	void initialize(){
		title.setText(BooksViewController.bookName);
		listOfNotes.setStyle("-fx-font-size: 2em ;");
		Book.loadNote("data/Notes.txt", BooksViewController.bookName);
		listOfNotes.getItems().addAll(Book.notesInBook);
    }
	
	@FXML
	public void select(ActionEvent event) {
		if(listOfNotes.getSelectionModel().getSelectedItem() != "No Notes Found" && listOfNotes.getSelectionModel().getSelectedItem() != null) {
			note = listOfNotes.getSelectionModel().getSelectedItem();
			noteText = Book.getNote("data/Notes.txt", note);
			try {
				Main.showViewNoteView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return;
		}
	}
	
	@FXML
	public void add(ActionEvent event) {
		
		try {
			Main.showAddNoteView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void handle(ActionEvent event) {
		try {
			Main.showBooksView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
