package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MusicController {

	@FXML 
	void goBack(ActionEvent event) {
		try {
			Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
			//Load the view for the type of machine chosen
			AnchorPane center = (AnchorPane)root.getCenter();
			//Set the center of the border pane depending on the type of machine chosen
			root.setCenter(center);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
			primaryStage.setTitle("Grounded");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
			System.out.println(e.toString());
		}
	}
}
