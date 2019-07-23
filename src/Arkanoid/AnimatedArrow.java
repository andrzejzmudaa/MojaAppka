package Arkanoid;


import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import static Arkanoid.GameScene.gameScene;
import static javafx.scene.input.KeyCode.*;


public class AnimatedArrow extends Polygon {

    double scale;
    public volatile boolean isActive=false;

    AnimatedArrow(double scale,double rotation, KeyCode klawisz){
            this.scale=scale;
            this.getPoints().addAll(new Double[]{
                    scalePoint(0), scalePoint(0),
                    scalePoint(0), scalePoint(2),
                    scalePoint(3), scalePoint(2),
                    scalePoint(3), scalePoint(3),
                    scalePoint(5), scalePoint(1),
                    scalePoint(3), scalePoint(-1),
                    scalePoint(3), scalePoint(0),});
            this.setFill(Color.RED);
            this.setRotate(rotation);








    }
    double scalePoint(double point){
        return scale*point; }

    void checkArrowColor(){

        if (this.isActive)
            this.setFill(Color.GREEN);
        else
            this.setFill(Color.RED);

    }



}
