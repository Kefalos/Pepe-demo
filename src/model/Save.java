package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.glass.events.KeyEvent;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Menu;

public class Save extends Stage{
	private Label lName = new Label("Enter your name: ");
	private TextField tName = new TextField();
	
	public Save(){
		HBox hb = new HBox();
		hb.getChildren().add(lName);
		hb.getChildren().add(tName);
		Scene scene = new Scene(hb);
		scene.setOnKeyPressed( e -> {

			if( e.getCode() == KeyCode.ENTER){
				save(tName.getText(), Menu.score);
			}
		});
		this.setTitle("Create new user");
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);;
		this.show();
	}
	
	private void save( String name, int score){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("highScores.txt"), true));
			bw.write(name + " : " + score + "\n");
			bw.close();
			Menu.score = 0; 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Save.this.close();
		new Menu();
	}
}
