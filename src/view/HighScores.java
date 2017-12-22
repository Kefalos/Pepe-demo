package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HighScores extends Stage{
	
	public HighScores(){
		ImagePattern background = new ImagePattern(new Image("/images/vatromet.jpg"));
		VBox vb = new VBox();
		VBox vb1 = new VBox();
		vb.getChildren().add(vb1);
		vb1.setAlignment(Pos.CENTER);
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("highScores.txt")));
			String s;
			int i = 0;
			while( ( s = br.readLine() )!= null ){
				vb1.getChildren().add(new Label(++i + ". " + s + " points"));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vb.setStyle("-fx-font : 28 'Arial';-fx-background: 'lightblue'");
		//vb.setStyle("-fx-background-image: url('/images/vatromet.jpg')");
		
		Scene scene = new Scene(vb,250,350);
		this.setScene(scene);
		this.setTitle("HIGH SCORES");
		this.initModality(Modality.APPLICATION_MODAL);
		this.show();

	}
}
