package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    Group group = new Group();
    Scene scene = new Scene(group, 1920, 1080);


    @Override
    public void start(Stage stage) throws Exception {
        int xDelta = 8;
        int yDelta = 38;
        Vecc2f position = new Vecc2f(100, 100);
        int boxWidth = 600;
        int boxHeight = 200;
        Textbox textbox = new Textbox("And let’s end all this nonsense about how long sentences = run-on sentences. You can have a six-word run-on sentence (I went shopping I ate donuts.)", position, xDelta, yDelta, boxWidth, boxHeight, 23);
        //Textbox textbox = new Textbox("this is a string of text string of long textttttttttttttttttt",position,xDelta,yDelta,boxWidth,boxHeight,group,scene);

        textbox.show(group, (float) 0.2);

        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
