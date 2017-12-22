package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.Game;
import view.Menu;


public class Main extends Application {
	private Menu m = new Menu();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			m.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
