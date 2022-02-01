package application.view;

import java.io.IOException;
import java.util.Optional;

import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class ViewNoteFromAllController implements EventHandler <ActionEvent> {

	@FXML
	private Label title;
	
	@FXML
	private Label saveAlert;
	
	@FXML
	private TextArea noteText;
	
	public String saveNote;
	
	@FXML
	void initialize(){
		title.setText(AllNotesViewController.note);
		noteText.setText(AllNotesViewController.noteText);
		saveNote = "";
    }
	
	@FXML
	public void save(ActionEvent event) throws InterruptedException {
		int i = -1;
		if(!AllNotesViewController.noteText.equals(noteText.getText()) && !saveNote.equals(noteText.getText())) {
			try {
				i = Book.saveNote("data/Notes.txt", AllNotesViewController.note, noteText.getText());
				if(i == 1) {
					saveAlert.setText("Note Saved");
					saveNote = noteText.getText();
				} else { saveAlert.setText("Note Could Not Be Saved");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void delete(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Delete");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete?");
		Optional <ButtonType> action = alert.showAndWait();
		
		Main.primaryStage.resizableProperty().setValue(true);
		Main.primaryStage.resizableProperty().setValue(false);
		
		if(action.get() == ButtonType.OK) {
			int i = -1;
			try {
				i = Book.deleteNote("data/Notes.txt", AllNotesViewController.note);
				if(i == 1) {
					Main.showAllNotesView();
				} else {
					saveAlert.setText("Could not delete Note");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			Main.showAllNotesView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
