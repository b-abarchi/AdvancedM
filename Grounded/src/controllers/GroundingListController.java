package controllers;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Random;

public class GroundingListController {
    
    @FXML private ToggleGroup grounding;
    @FXML private Button groundingSelection;
    
    @FXML
    void startGrounding(ActionEvent event) {
        RadioButton rb = (RadioButton)grounding.getSelectedToggle();
        String selection = rb.getText();
        String fxmlFile = "";
        
        switch(selection) {
	        case "5-4-3-2-1":
	            fxmlFile = "../views/fiveToOneTechnique.fxml";
	            changeScreen(event, fxmlFile, "5-4-3-2-1");
	            break;
	        case "Categories":
	        	fxmlFile = "../views/categoriesTechnique.fxml";
	            changeScreen(event, fxmlFile, "Categories");
	            break;
	        case "Awareness":
	        	fxmlFile = "../views/awerenessTechnique.fxml";
	            changeScreen(event, fxmlFile, "Awareness");
	            break;
	        case "Mental Health":
	        	fxmlFile = "../views/mentalExerciseTechnique.fxml";
	            changeScreen(event, fxmlFile, "Mental Exercise");
	            break;
        }
    }
	
	
	@FXML 
	void goBack(ActionEvent event) {
		HelperController hc = new HelperController();
		hc.goBack(event);
	}
	
	@FXML
    void btnBack(ActionEvent event) {
		try {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
            //Load the view for the type of options chosen
            AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource("../views/GroundingList.fxml"));
            //Set the center of the border pane depending on the type of options chosen
            root.setCenter(center);
            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());

            primaryStage.setTitle("Groudning List");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void btnNext(ActionEvent event) {
    	try {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
            //Load the view for the type of options chosen
            AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource("../views/categoriesTechnique.fxml"));
            //Set the center of the border pane depending on the type of options chosen
            root.setCenter(center);
            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();
            String title = primaryStage.getTitle();
            ObservableList <Node> children = center.getChildren();
            TextArea txt = (TextArea) children.get(0);
            if(title.contains("Categories")) {
             	txt.setText(categories(randomNumberZeroToEleven()));
         	}
             else if(title.contains("Awareness")) {
             	txt.setText(awareness(randomNumberZeroToSeven()));
         	}
             else if(title.contains("Mental Exercise")) {
             	txt.setText(mentalExercises(randomNumberZeroToSeven()));
         	}
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }
    
    void changeScreen(ActionEvent event, String fxmlFile, String title) {
        try {
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
            //Load the view for the type of technique chosen
            AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
            //Set the center of the border pane depending on the type of technique chosen
            root.setCenter(center);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            ObservableList <Node> children = center.getChildren();
            TextArea txt = (TextArea) children.get(0);
            if(title.contains("5-4-3-2-1")) {
            	txt.setText(fiveToOne());
        	}
            else if(title.contains("Categories")) {
            	txt.setText(categories(randomNumberZeroToEleven()));
        	}
            else if(title.contains("Awareness")) {
            	txt.setText(awareness(randomNumberZeroToSeven()));
        	}
            else if(title.contains("Mental Exercise")) {
            	txt.setText(mentalExercises(randomNumberZeroToSeven()));
        	}
            primaryStage.show();
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }
	
	private String fiveToOne() {
		String fiveToOne = "What are 5 things you can see? Look for small details such as a pattern on the \r\n"
				+ "ceiling, the way light reflects off a surface, or an object you never noticed.\r\n"
				+ "What are 4 things you can feel? Notice the sensation of clothing on your body, the \r\n"
				+ "sun on your skin, or the feeling of the chair you are sitting in. Pick up an object and \r\n"
				+ "examine its weight, texture, and other physical qualities.\r\n"
				+ "What are 3 things you can hear? Pay special attention to the sounds your mind \r\n"
				+ "has tuned out, such as a ticking clock, distant traffic, or trees blowing in the wind.\r\n"
				+ "What are 2 things you can smell? Try to notice smells in the air around you, like an \r\n"
				+ "air freshener or freshly mowed grass. You may also look around for something \r\n"
				+ "that has a scent, such as a flower or an unlit candle.\r\n"
				+ "What is 1 thing you can taste? Carry gum, candy, or small snacks for this step. Pop \r\n"
				+ "one in your mouth and focus your attention closely on the flavors.";
		return fiveToOne;
	}
	
	private String categories(int num) {
		String category = "";
		switch(num) {
			case 0: category = "Movies";
				break;
			case 1: category = "Sports Teams";
				break;
			case 2: category = "Animals";
				break;
			case 3: category = "Countries";
				break;
			case 4: category = "Colors";
				break;
			case 5: category = "Cities";
				break;
			case 6: category = "Books";
				break;
			case 7: category = "Cars";
				break;
			case 8: category = "Tv Shows";
				break;
			case 9: category = "Cereals";
				break;
			case 10: category = "Fruits or Vegetables";
				break;
			case 11: category = "Famous People";
				break;
		}
		String categories = "Name as many " + category + " as you can think of in one minute.";
		return categories;
	}
	
	private String mentalExercises(int num) {
		String exercise = "";
		
		switch(num) {
			case 0: exercise = "Name all the objects you see.";
				break;
			case 1: exercise = "Describe the steps in performing an activity you know how to do well. For example, \r\n"
					+ "how to shoot a basketball, prepare your favorite meal, or tie a knot.";
				break;
			case 2: exercise = "Count backwards from 100 by 7.";
				break;
			case 3: exercise = "Pick up an object and describe it in detail. Describe its color, texture, size, weight, \r\n"
					+ "scent, and any other qualities you notice.";
				break;
			case 4: exercise = "Spell your full name, and the names of three other people, backwards.";
				break;
			case 5: exercise = "Name all your family members, their ages, and one of their favorite activities.";
				break;
			case 6: exercise = "Read something backwards, letter-by-letter. Practice for at least a few minutes.";
				break;
			case 7: exercise = "Think of an object and \"draw\" it in your mind, or in the air with your finger. Try drawing \r\n"
					+ "your home, a vehicle, or an animal.";
				break;
		}
		return exercise;
	}
	
	private String awareness(int num) {
		String activity = "";
		
		switch(num) {
			case 0: activity = "Take 5 long, deep breaths through your nose, and exhale through puckered lips.";
				break;
			case 1: activity = "Place both feet flat on the floor. Wiggle your toes. Curl and uncurl your toes several \r\n"
					+ "times. Spend a moment noticing the sensations in your feet.";
				break;
			case 2: activity = "Stomp your feet on the ground several times. Pay attention to the sensations in your feet \r\n"
					+ "and legs as you make contact with the ground.";
				break;
			case 3: activity = "Clench your hands into fists, then release the tension. Repeat this 10 times.";
				break;
			case 4: activity = "Press your palms together. Press them harder and hold this pose for 15 seconds. Pay \r\n"
					+ "attention to the feeling of tension in your hands and arms.";
				break;
			case 5: activity = " Rub your palms together briskly. Notice and sound and the feeling of warmth.";
				break;
			case 6: activity = "Reach your hands over your head like you’re trying to reach the sky. Stretch like this for 5 \r\n"
					+ "seconds. Bring your arms down and let them relax at your sides.";
				break;
			case 7: activity = "Take 5 more deep breaths and notice the feeling of calm in your body.";
				break;
		}
		return activity;
	}
	
	
	//Helper method(s)
	private int randomNumberZeroToEleven() {
		Random rand = new Random();
		int randNum = rand.nextInt((11 + 1));
		
		return randNum;
	}
	
	private int randomNumberZeroToSeven() {
		Random rand = new Random();
		int randNum = rand.nextInt((7 + 1));
		
		return randNum;
	}
}