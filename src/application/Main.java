package application;
	
import gui.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/SelectionView.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Blacklist para WhatsApp");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			Utils.showAlert("Erro inesperado", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
