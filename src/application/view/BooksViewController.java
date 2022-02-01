package application.view;


import java.io.IOException;

import application.Main;
import application.model.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;


public class BooksViewController implements EventHandler <ActionEvent> {
	@FXML
	private Button back;
	
	@FXML
	private TabPane tab;
	
	@FXML
	private ListView<String> otList;
	
	@FXML
	private ListView<String> ntList;
	
	@FXML
	private ListView<String> aList;
	
	public static String bookName;
	
	@FXML
	void initialize(){
		otList.setStyle("-fx-font-size: 2em ;");
		ntList.setStyle("-fx-font-size: 2em ;");
		aList.setStyle("-fx-font-size: 2em ;");
		Book.loadOldTestament("data/OldTestament.txt");
		Book.loadNewTestament("data/NewTestament.txt");
		otList.getItems().addAll(Book.ot);
		ntList.getItems().addAll(Book.nt);
		aList.getItems().addAll(Book.alph);
    }
	
	@Override
	public void handle(ActionEvent event) {
		try {
			Main.showMainMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void select(ActionEvent event) {
		if(tab.getSelectionModel().getSelectedItem().getText().equals(" Old Testament ")) {
			bookName = otList.getSelectionModel().getSelectedItem();
		} else if(tab.getSelectionModel().getSelectedItem().getText().equals(" New Testament ")) {
			bookName = ntList.getSelectionModel().getSelectedItem();
		} else if(tab.getSelectionModel().getSelectedItem().getText().equals(" Alphebetical ")) {
			bookName = aList.getSelectionModel().getSelectedItem();
		}
		
		if(bookName == null) { return; }
		
		try {
			Main.showNotesByBookView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
