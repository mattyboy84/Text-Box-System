package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class Textbox {

    Circle[] circles = new Circle[4];
    String sentence;
    ArrayList<Word> words = new ArrayList<>();
    String[] tempARR;
    int pointer = 0;
    /*
    int xDelta = 8;
        int yDelta = 38;
        Vecc2f position = new Vecc2f(100, 100);
        int boxWidth = 600;
        int boxHeight = 200;
        Textbox textbox = new Textbox("And let’s end all this nonsense about how long sentences = run-on sentences. You can have a six-word run-on sentence (I went shopping I ate donuts.)", position, xDelta, yDelta, boxWidth, boxHeight, 23);
        //Textbox textbox = new Textbox("this is a string of text string of long textttttttttttttttttt",position,xDelta,yDelta,boxWidth,boxHeight,group,scene);

        textbox.show(group, (float) 0.2);
     */

    public Textbox(String text, Vecc2f position, int xDelta, int yDelta, int boxWidth, int boxHeight, int fontSize) {
        this.sentence = text;
        tempARR = this.sentence.split(" ");
        for (int i = 0; i < tempARR.length; i++) {
            words.add(new Word(tempARR[i]));
            //
            words.get(i).setFont(Font.font("Upheaval", FontWeight.BOLD, fontSize));
        }
        for (int i = 0; i < words.size(); i++) {
            if (i == 0) {
                words.get(i).relocate(position.x, position.y);
            } else {
                words.get(i).relocate((float) (words.get(i - 1).getPosition().x + words.get(i - 1).getText().getBoundsInParent().getWidth() + xDelta), words.get(i - 1).getPosition().y);
                if (words.get(i).getText().getBoundsInParent().getMaxX() > (position.x + boxWidth)) {
                    words.get(i).relocate(position.x, words.get(i - 1).getPosition().y + yDelta);
                }
            }
        }
    }

    public void show(Group group, float timeDiff) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(timeDiff), event -> {
            words.get(pointer).phaseIn(group, timeDiff);
            pointer++;

        }));
        timeline.setCycleCount(words.size());
        timeline.play();

        /*
        for (Word word : words) {
            group.getChildren().add(word.getText());
        }
         */
    }

    public static class Word {

        Text text;
        Vecc2f position;
        float cycleCount = 0;
        int maxCycle = 1000;

        public Word(String s) {
            this.text = new Text(s);
            this.position = new Vecc2f(0, 0);
            this.text.setOpacity(0);

        }

        public void phaseIn(Group group, float timeDiff) {

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds((float) 1 / maxCycle), event -> {
                cycleCount++;
                //System.out.println((cycleCount/100));
                text.setOpacity(0 + (cycleCount / (maxCycle/10)));

            }));
            timeline.setCycleCount((maxCycle/10));
            timeline.play();


            group.getChildren().add(text);

        }

        public void relocate(float x, float y) {
            this.position.set(x, y);
            this.text.relocate(x, y);
        }

        public Text getText() {
            return text;
        }

        public void setText(Text text) {
            this.text = text;
        }

        public Vecc2f getPosition() {
            return position;
        }

        public void setPosition(Vecc2f position) {
            this.position = position;
        }

        public void setFont(Font upheaval) {
            this.text.setFont(upheaval);
        }
    }

}