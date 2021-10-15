package controllers;

import java.io.IOException;

import app.LiveChat;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController {

    @FXML private Text appName;
    @FXML private ToggleGroup options;
    @FXML private Button btnStart, btnRandom;

    @FXML
    void startProgram(ActionEvent event) {
    	RadioButton selected = (RadioButton)options.getSelectedToggle();
    	String selection = selected.getText();
    	String fxmlFile = "";
    	
    	switch(selection) {
    	case "Grounding":
    		fxmlFile = "../views/GroundingList.fxml";
    		changeScreen(event, fxmlFile, "Grounding List");
    		break;
    	case "Articles":
    		fxmlFile = "../views/articles.fxml";
    		changeScreen(event, fxmlFile, "Article Links");
    		break;
    	case "Breathing":
    		fxmlFile = "../views/breathing.fxml";
    		changeScreen(event, fxmlFile, "Breathing Techniques");
    		break;
    	case "Music":
    		fxmlFile = "../views/music.fxml";
    		changeScreen(event, fxmlFile, "Music Player");
    		break;
    	case "Places To Go":
    		fxmlFile = "../views/placesToGo.fxml";
    		changeScreen(event, fxmlFile, "Places To Go");
    		break;
    	case "Chat with a Doctor":
    		LiveChat liveChat = new LiveChat();
    		LiveChat liveChat2 = new LiveChat();
    		Platform.runLater(new Runnable() {
    			public void run() {
    				try {
						liveChat.runChat("Server");
						liveChat2.runChat("Client");
					} catch (Exception e) {
						e.printStackTrace();
					}
    			}
    		});
    		break;
    	}
    }

    @FXML
    void startProgramRandom(ActionEvent event) {
    	String[] options = new String[] {
    		"Grounding",
    		"Articles",
    		"Breathing",
    		"Music",
    		"Places To Go"
    	};
    	
    	int rand = (int)(Math.random() * options.length);
    	System.out.println(rand);
    	String chosen = options[rand];
    	System.out.println(chosen);
    	String fxmlFile = "";
    	
    	switch(chosen) {
    	case "Grounding":
    		fxmlFile = "../views/GroundingList.fxml";
    		changeScreen(event, fxmlFile, "Grounding List");
    		break;
    	case "Articles":
    		fxmlFile = "../views/articles.fxml";
    		changeScreen(event, fxmlFile, "Article Links");
    		break;
    	case "Breathing":
    		fxmlFile = "../views/breathing.fxml";
    		changeScreen(event, fxmlFile, "Breathing Techniques");
    		break;
    	case "Music":
    		fxmlFile = "../views/music.fxml";
    		changeScreen(event, fxmlFile, "Music Player");
    		break;
    	case "Places To Go":
    		fxmlFile = "../views/placesToGo.fxml";
    		changeScreen(event, fxmlFile, "Places To Go");
    		break;
    	case "Chat with a Doctor":
    		fxmlFile = "../views/chat.fxml";
//    		changeScreen(event, fxmlFile, "Chat");
    	}
    }

    void changeScreen(ActionEvent event, String fxmlFile, String title) {
    	if(title.equals("Music Player")) {
            try {
            	 Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				 BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
				
				 //Load the view for the type of option chosen
				 AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
				
				 //Create a WebView and a WebEngine object
				 final WebView browser = new WebView();
				 final WebEngine webEngine = browser.getEngine();
                 webEngine.load("https://www.youtube.com/watch?v=9wIwBWkmyes&list=OLAK5uy_n6K9Np1PQyIIZIDTx_6dlTrkOMb-Q3Jn0&index=1");

                 //Get the children of the page to be loaded as center of the main border pane
 				 ObservableList<Node> children = center.getChildren();
 				 System.out.println(children);
 				 //Extract the VBox
 				 VBox vbox = (VBox)children.get(0);
	             System.out.println(vbox);
 				 //Add the embedded browser to the VBox
 				 vbox.getChildren().addAll(browser);
 				
 				 //Set the "center" as center of the root (BorderPane).
 				 root.setCenter(center);
 				 Scene scene = new Scene(root);
 				 scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
 				 primaryStage.setTitle(title);
 				 primaryStage.setScene(scene);
 				 primaryStage.show();
            } catch(IOException e) {
                System.out.println(e);
            }
        }
    	
    	if(title.contains("Article")) {
            //Create the arrays of captions and urls
            final String[] captions = new String[] {
                "Panic Attack",
                "Stop a Panic Attack",
                "Living With Anxiety Pointers",
                "10 Tips for Managing Anxiety",
                "Tips to Manage Anxiety and Stress",
                "Panic Attacks and Panic Disorder"
            };

            final String[] urls = new String[] {
                "https://www.healthline.com/health/anxiety/panic-attack-self-care-strategies",
                "https://www.healthline.com/health/how-to-stop-a-panic-attack#happy-place",
                "https://www.webmd.com/anxiety-panic/anxiety-tips",
                "https://www.therecoveryvillage.com/mental-health/anxiety/related/self-help-for-anxiety/",
                "https://adaa.org/tips",
                "https://www.mayoclinic.org/diseases-conditions/panic-attacks/diagnosis-treatment/drc-20376027"
            };
    		
    		//Create an array of Hyperlinks
    		final Hyperlink[] hpls1 = new Hyperlink[captions.length/2];
    		final Hyperlink[] hpls2 = new Hyperlink[captions.length/2];
    		
    		//Change the scene
    		try {
				Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
				
				//Load the view for the type of option chosen
				AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
				
				//Create a WebView and a WebEngine object
				final WebView browser = new WebView();
				final WebEngine webEngine = browser.getEngine();
				
				//Loop through the captions and create a hyperlink (hlp)
				//Store the hlp in the hyperlinks (hpls) array initialized with captions[i]
				for(int i = 0, j = 3; i < captions.length/2; i++, j++) {
					final Hyperlink hpl = hpls1[i] = new Hyperlink(captions[i]);
					final Hyperlink hlp2 = hpls2[i] = new Hyperlink(captions[j]);
					hpl.setFont(Font.font("Arial", 14));
					hlp2.setFont(Font.font("Arial", 14));
					
					//Create a url initialized with urls[i]
					final String url = urls[i];
					final String url2 = urls[i+1];
					
					//set an ActionEvent on each link to open a browser with the link 
					hpl.setOnAction((ActionEvent e) -> {
						webEngine.load(url);
					});
					
					hlp2.setOnAction((ActionEvent e) -> {
						webEngine.load(url2);
					});
				}
				
				//Get the children of the page to be loaded as center of the main border pane
				ObservableList<Node> children = center.getChildren();
				//Extract the VBox
				VBox vbox = (VBox)children.get(0);
				
				//Get the children of the VBox
				ObservableList<Node> vChildren = vbox.getChildren();
				//Extract the HBox
				HBox hbox = (HBox)vChildren.get(0);
				HBox hbox2 = (HBox)vChildren.get(1);
				
				//Add the hyperlinks to the HBox
				hbox.getChildren().addAll(hpls1);
				hbox.setSpacing(25);
				
				hbox2.getChildren().addAll(hpls2);
				hbox2.setSpacing(25);
				
				//Add the embedded browser to the VBox
				vbox.getChildren().addAll(browser);
				
				//Set the "center" as center of the root (BorderPane).
				root.setCenter(center);
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
				primaryStage.setTitle(title);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(IOException e) {
				System.out.println(e.toString());
			}
    		
    	} else {
    		try {
				Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../views/Main.fxml"));
				//Load the view for the type of options chosen
				AnchorPane center = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
				//Set the center of the border pane depending on the type of options chosen
				root.setCenter(center);
				Scene scene = new Scene(root);
				
				if(!title.contains("Breathing")) {
					scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
				} else {
					scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/breathing.css").toExternalForm());
				}
				primaryStage.setTitle(title);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(IOException e) {
				System.out.println(e.toString());
			}
    	}
    }
}
