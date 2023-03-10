package application;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application   {
	@Override
	public void start(Stage arg0) throws Exception {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
			 Scene scene = new Scene(root);
	    	  
	    	  Stage stage = new Stage();
	    	  stage.setScene(scene);
	    	  stage.setResizable(false);
	    	  stage.setTitle("Luxurious Hotel");
	    	  stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
		
	}
    public static void main(String[] args) {
        Application.launch(args);
    }
}