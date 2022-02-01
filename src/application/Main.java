package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static Stage primaryStage;
	private static AnchorPane mainLayout;
	private static AnchorPane bookLayout;
	private static AnchorPane notesByBookLayout;
	private static AnchorPane viewNoteLayout;
	private static AnchorPane addNoteLayout;
	private static AnchorPane allNotesLayout;
	private static AnchorPane viewNoteFromAllLayout;
	private static AnchorPane addNoteFromAllLayout;
	private static AnchorPane searchLayout;
	public static Stage newStage;



	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		//Main.primaryStage.resizableProperty().setValue(false);
		Main.primaryStage.setResizable(false);
		showMainMenu();
	}
	
	public static void showMainMenu() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
//		if(primaryStage.resizableProperty().getValue() == true) {
//			primaryStage.resizableProperty().setValue(Boolean.FALSE);
//
//		}
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showBooksView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/BooksView.fxml"));
		bookLayout = loader.load();
		Scene scene = new Scene(bookLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showNotesByBookView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/NotesByBook.fxml"));
		notesByBookLayout = loader.load();
		Scene scene = new Scene(notesByBookLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showViewNoteView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/ViewNote.fxml"));
		viewNoteLayout = loader.load();
		Scene scene = new Scene(viewNoteLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showAddNoteView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddNote.fxml"));
		addNoteLayout = loader.load();
		Scene scene = new Scene(addNoteLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showAllNotesView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AllNotesView.fxml"));
		allNotesLayout = loader.load();
		Scene scene = new Scene(allNotesLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showViewNoteFromAllView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/ViewNoteFromAll.fxml"));
		viewNoteFromAllLayout = loader.load();
		Scene scene = new Scene(viewNoteFromAllLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showAddNoteFromAllView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddNoteFromAll.fxml"));
		addNoteFromAllLayout = loader.load();
		Scene scene = new Scene(addNoteFromAllLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void showSearchView() throws IOException{		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SearchView.fxml"));
		searchLayout = loader.load();
		Scene scene = new Scene(searchLayout);
		newStage = new Stage();
//		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.setScene(scene);
//		newStage.show();
		newStage.setResizable(false);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.showAndWait();
		
		Main.primaryStage.resizableProperty().setValue(true);
		Main.primaryStage.resizableProperty().setValue(false);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
