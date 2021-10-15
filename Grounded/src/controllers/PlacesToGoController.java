package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PlacesToGoController {
	 @FXML private HBox placesHbox;
	 @FXML private VBox placesVbox;
	 @FXML private WebView webView;
	 
	 @FXML
	 void placesParks(ActionEvent event) {
		 WebEngine engine = webView.getEngine();
		    engine.load("https://www.google.com/search?q=local+parks");
	 }
	 
	 @FXML
	 void placesTrails(ActionEvent event) {
		 WebEngine engine = webView.getEngine();
		    engine.load("https://www.google.com/search?q=local+trails");
	 }
	 
	 @FXML
	 void placesPsycologists(ActionEvent event) {
		 WebEngine engine = webView.getEngine();
		    engine.load("https://www.google.com/search?q=local+psychologist");
	 }
	 
	 @FXML
	 void placesCounselors(ActionEvent event) {
		 WebEngine engine = webView.getEngine();
		    engine.load("https://www.google.com/search?q=local+counselor");
	 }

	@FXML 
	void goBack(ActionEvent event) {
		HelperController hc = new HelperController();
		hc.goBack(event);
	}
}
