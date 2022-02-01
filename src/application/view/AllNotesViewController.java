package application.view;

import java.io.IOException;

import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class AllNotesViewController implements EventHandler <ActionEvent> {
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
		listOfNotes.setStyle("-fx-font-size: 2em ;");
		Book.loadOldTestament("data/OldTestament.txt"); Book.loadNewTestament("data/NewTestament.txt");
		Book.loadNote("data/Notes.txt", "all");
		listOfNotes.getItems().addAll(Book.notesInBook);
		
    }
	@FXML
	public void select(ActionEvent event) {
		if(listOfNotes.getSelectionModel().getSelectedItem() != "No Notes Found" && listOfNotes.getSelectionModel().getSelectedItem() != null) {
			note = listOfNotes.getSelectionModel().getSelectedItem();
			noteText = Book.getNote("data/Notes.txt", note);
			try {
				Main.showViewNoteFromAllView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return;
		}
	}
	
	@FXML
	public void add(ActionEvent event) {
		try {
			Main.showAddNoteFromAllView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void search(ActionEvent event) throws IOException {
		try {
			Main.showSearchView();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			Main.showMainMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
