package view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu extends Stage{
	public static double W = 600;
	public static double H = 600;
	public static int score = 0;
	private Button play = new Button("PLAY");
	private Button options = new Button("OPTIONS");
	private Button highScores = new Button("HIGH SCORES");
	private Button exit = new Button("EXIT");
	
	public Menu(){
		/*String path = new File("C:/Users/Aca/Desktop/JAVA/miroljub demo/src/sounds/intro.mp3").toURI().toString();
		Media m = new Media(path);
		MediaPlayer mp = new MediaPlayer(m);
		MediaView mv = new MediaView(mp);
		mp.setAutoPlay(true);
		mp.setCycleCount(Integer.MAX_VALUE);*/
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color: #FF0000;");
		vb.getChildren().addAll(play, options, highScores, exit);
		
		//vb.getChildren().add(mv);
		
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		play.setMinWidth(200);
		options.setMinWidth(200);
		highScores.setMinWidth(200);
		exit.setMinWidth(200);
		play.setStyle("-fx-font:24 Arial;-fx-background-color: 'black'");
		options.setStyle("-fx-font:24 Arial;-fx-background-color: 'black'");
		highScores.setStyle("-fx-font:24 Arial;-fx-background-color: 'black'");
		exit.setStyle("-fx-font:24 Aria;-fx-background-color: 'black'");
		play.setTextFill(Color.YELLOW);
		highScores.setTextFill(Color.RED);
		options.setTextFill(Color.PURPLE);
		exit.setTextFill(Color.LIGHTBLUE);
		
		
		
		play.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				/*mp.setAutoPlay(false);
				mp.stop();*/
				new Game();
				Menu.this.close();
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setContentText("Are you sure you want to leave?");
				a.showAndWait();
				System.out.println(a.getResult().getText());
				if( a.getResult().getText().equals("OK"))
				Menu.this.close();
			}
		});
		highScores.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				new HighScores();
			}
		});
		Scene scene = new Scene(vb,W,H);
		//scene.setFill(Color.BLACK);
		this.setScene(scene);
		this.setTitle("P.E.P.E.");
		this.show();
	}
}
