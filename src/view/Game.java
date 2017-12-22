package view;

import java.awt.Font;
import java.io.File;
import java.util.Random;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Save;

public class Game extends Stage {
	private boolean isEscape = false;
	private static final AudioClip ting = new AudioClip(new File("C:/Users/Aca/Desktop/JAVA/miroljub demo/src/sounds/ting.mp3").toURI().toString()); 
	private Image normal = new Image("/images/normal.jpg");
	private Image angry = new Image("/images/angry.jpg");
	private Image sad = new Image("/images/sad.jpg");
	private Image thinking = new Image("/images/thinking.jpg");
	private Image sand = new Image("/images/sand.jpg");
	private Image normie = new Image("/images/normie.jpg");
	private BooleanProperty upPressed = new SimpleBooleanProperty();
	private BooleanProperty downPressed = new SimpleBooleanProperty();
	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanBinding rightOrDown = upPressed.or(rightPressed).or(downPressed).or(leftPressed);
	private Label score  = new Label("SCORE: " + Menu.score); 
	private Label esc = new Label("Press ESC to quit");
	
	String path = new File("C:/Users/Aca/Desktop/JAVA/pepe demo/src/sounds/theme.mp3").toURI().toString();
	Media m = new Media(path);
	MediaPlayer mp = new MediaPlayer(m);
	MediaView mv = new MediaView(mp);
	private AnimationTimer timer = new AnimationTimer() {
		
		@Override
		public void handle(long now) {
			double x = 0, y = 0;
			if (rightPressed.get()) x+=delta;  
			if (leftPressed.get()) x-=delta;
			if (upPressed.get()) y-=delta;
			if (downPressed.get()) y+=delta;
			//System.out.println(rightPressed.get() + " " + leftPressed.get()+ " " +upPressed.get()+ " " +downPressed.get());
			//System.out.println(x + " " + y);
			move(x,y);
			normieRun();
			if( Math.abs(cPepe.getCenterX() - cNormie.getCenterX() ) <= 30 && Math.abs(cPepe.getCenterY() - cNormie.getCenterY() ) <= 30 ){
				eaten();
			}
		}
	};
	
	private int delta = 5;
	public Game() {
		super();
		mp.setAutoPlay(true);
		BorderPane root = new BorderPane();
		root.getChildren().add(mv);
		Scene scene = new Scene(root,Menu.W,Menu.H);
		root.setStyle("-fx-background-image: url('/images/sand.jpg');-fx-background-repeat: no-repeat");
		this.setScene(scene);
		this.setTitle("P.E.P.E");
		this.show();
		root.getChildren().add(cPepe);
		root.getChildren().add(cNormie);
		cNormie.setRadius(15);
		cNormie.setFill(new ImagePattern(normie));
		spawn();
		root.setTop(score);
		root.setBottom(esc);
		esc.setStyle("-fx-font: 16 Arial");
		score.setStyle("-fx-text-fill: 'red'");
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()){
				case UP: upPressed.set(true); break;
				case DOWN: downPressed.set(true); break;
				case LEFT: leftPressed.set(true); break;
				case RIGHT: rightPressed.set(true); break;
				case ESCAPE:{
					quit();
				} break;
				default: break;
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()){
				case UP: upPressed.set(false); break;
				case DOWN: downPressed.set(false); break;
				case LEFT: leftPressed.set(false); break;
				case RIGHT: rightPressed.set(false); break;
				}
				
			}
		});
		
		if( isEscape){
			quit();
		} else{
			rightOrDown.addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(newValue){
						timer.start();
					} else {
						timer.stop();
					}
					
				}
			});
		}


		
	}
	//PEPE
	private Circle cPepe = new Circle(100,100,20,new ImagePattern(normal));
	private void move(double x, double y){
		if( (cPepe.getCenterX() - 20 + x ) >= 0 && 
			(cPepe.getCenterX() + 20 + x ) <= Menu.W && 
			(cPepe.getCenterY() - 20 + y ) >= 0 &&
			(cPepe.getCenterY() + 20 + y ) <= Menu.H){
			cPepe.setCenterX(cPepe.getCenterX() + x);
			cPepe.setCenterY(cPepe.getCenterY() + y);
		}
	}
	//NORMIE
	private Circle cNormie = new Circle();
	private void eaten(){
		spawn();
		Menu.score++;
		score.setText("SCORE: " + Menu.score);
		ting.play();
	}
	private void spawn(){
		Double a = Menu.W;
		cNormie.setCenterX(randomize(a.intValue()));
		a = Menu.H;
		cNormie.setCenterY(randomize(a.intValue()));
	}
	private int randomize(int bound){
		int r;
		Random rand = new Random();
		r = rand.nextInt(bound);
		return r;
	}
	private void normieRun(){
		if(cPepe.getCenterX() < cNormie.getCenterX() ){ // ako je levo od normija normi bezi desno
			if( cNormie.getCenterX() <= ( Menu.W - 16) ){
				cNormie.setCenterX(cNormie.getCenterX() + 1 );
			}
		} else if( cPepe.getCenterX() > cNormie.getCenterX() ){ // ako je desno, normi bezi levo
			if( cNormie.getCenterX() >= 16 ){
				cNormie.setCenterX(cNormie.getCenterX() - 1);
			}
		}
		
		if(cPepe.getCenterY() < cNormie.getCenterY() ){ // ako je iznad od normija normi bezi dole
			if( cNormie.getCenterY() <= ( Menu.H - 16) ){
				cNormie.setCenterY(cNormie.getCenterY() + 1 );
			}
		} else if( cPepe.getCenterY() > cNormie.getCenterY() ){ // ako je ispod, normi bezi gore
			if( cNormie.getCenterY() >= 16 ){
				cNormie.setCenterY(cNormie.getCenterY() - 1);
			}
		}
	}
	
	private void quit(){
		//Alert quitAlert = new Alert(AlertType.CONFIRMATION);
		timer.stop();
		//quitAlert.setContentText("Are you sure you want to quit?");
		//quitAlert.showAndWait();
		//if( quitAlert.getResult().getText().equals("Cancel")){
		//	timer.start();
	//	}
	//	if( quitAlert.getResult().getText().equals("OK")){
			mp.stop();
			Game.this.close();
			new Save();
			//new Menu();
			//Menu.score = score;
		//}
	}
	
}
