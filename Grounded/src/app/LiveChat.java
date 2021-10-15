package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LiveChat extends Application implements Runnable {
	private boolean isServer;
	private Connection connection = isServer ? createServer() : createClient();
	private TextArea messages = new TextArea();
	private TextField input = new TextField();
	
	private Parent createContent() throws Exception {
		BorderPane root = new BorderPane();
		messages.setPrefHeight(370);
		messages.setEditable(false);
		input.setPrefSize(270, 25);
		Button send = new Button("Send");
		send.getStyleClass().add("btn");
		send.setPrefSize(100, 25);
		HBox hbox = new HBox(input, send);
		hbox.setSpacing(10);
		VBox vbox = new VBox(10, messages, hbox);
		VBox.setMargin(hbox, new Insets(10));
		VBox.setMargin(messages, new Insets(10));
		root.setCenter(vbox);
		root.setPrefSize(400, 400);
		
		send.setOnAction(event -> {
			String message = isServer ? "[Doctor]: " : "[Me]: ";
			message += input.getText();
			input.clear();
			messages.appendText(message + "\n");
			
			try {
				connection.send(message);
				System.out.println(connection.getIP() + " " + connection.getPort());
			} catch (Exception e) {
				messages.appendText("Failed to send.\n");
				System.out.println(e.getCause());
			}
		});
		
		return root;
	}
	
	//Start the connection
	//This runs before main. It is called by JavaFx
	@Override
	public void init() throws Exception {
		connection.startConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent content = createContent();
		Scene scene = new Scene(content);
		scene.getStylesheets().add(getClass().getResource("../assets/styleSheets/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chat with a Doctor");
		primaryStage.show();
	}
	
	//Stop connection
	@Override
	public void stop() throws Exception {
		connection.closeConnection();
	}
	

	
	//Create client
	private Client createClient() {
		return new Client(data -> {
			Platform.runLater(() -> {
				//Using Platform.runLater because we are dealing with a background thread
				//different from the UI thread - This control back to UI thread
				messages.appendText(data.toString() + "\n");
			});
		}, "127.0.0.1", 3000);
	}
	
	//Create server	
	private Server createServer() {
		return new Server(data -> {
			//Using Platform.runLater because we are dealing with a background thread
			//different from the UI thread - This control back to UI thread
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");
			});
		}, 3000);
	}

	@Override
	public void run() {
		launch();	
	}
	
	public void runChat(String type) throws Exception {
		if(type.equals("Server")) {
			this.isServer = true;
		} else {
			this.isServer = false;
		}
		start(new Stage());
	}
}
