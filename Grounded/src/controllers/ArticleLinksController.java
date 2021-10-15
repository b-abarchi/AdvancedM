package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ArticleLinksController {

	@FXML 
	void goBack(ActionEvent event) {
		HelperController hc = new HelperController();
		hc.goBack(event);
	}
}
