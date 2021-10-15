package controllers;

import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BreathingController{
	
    @FXML private TextField txtBreathing, txtTime;
    
    int secondsPassed = 0;
    int numcount = 1;
    Timer myTimer = new Timer(); 
	TimerTask task = new TimerTask() {
		public void run() {
			secondsPassed++;
			txtTime.setText(String.format("00:%02d", 30-secondsPassed));
			if(secondsPassed%3==0) {
				if(numcount%2 == 0) {
					txtBreathing.setText("Breathe in");
					numcount++;
				}
				else {
					txtBreathing.setText("Breathe out");
					numcount++;
				}
			}
			if(secondsPassed == 30) {
				txtBreathing.setText("Good Job!");
				myTimer.cancel();
			}
		}
	};

	@FXML 
	void goBack(ActionEvent event) {
		HelperController hc = new HelperController();
		hc.goBack(event);
	}
	
    @FXML
    void startTimer(ActionEvent event)  {
    	txtBreathing.setText("Breathe in");
    	txtTime.setText("00:30");
		start();
    }
    
	public void start() {
		myTimer.scheduleAtFixedRate(task, 1000, 1000);
  }
}