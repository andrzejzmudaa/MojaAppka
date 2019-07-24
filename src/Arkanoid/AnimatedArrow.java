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
    double rotationAngle;

    AnimatedArrow(double scale,double rotation, KeyCode klawisz){
            this.scale=scale;
            this.rotationAngle=rotation;
            this.getPoints().addAll(new Double[]{
                    scalePoint(0), scalePoint(0),
                    scalePoint(0), scalePoint(2),
                    scalePoint(3), scalePoint(2),
                    scalePoint(3), scalePoint(3),
                    scalePoint(5), scalePoint(1),
                    scalePoint(3), scalePoint(-1),
                    scalePoint(3), scalePoint(0),});
            this.setFill(Color.RED);
            this.setRotate(rotationAngle);








    }
    double scalePoint(double point){
        return scale*point; }

    void checkArrowColor(){

        if (this.isActive)
            this.setFill(Color.GREEN);
        else
            this.setFill(Color.RED);

    }

    int checkArrowDirection(){
        // These function is returning arrow direction as int:
        // 0 - not known direction
        // 1 - UP
        // 2 - DOWN
        // 3 - LEFT
        // 4 - RIGHT
        if (this.rotationAngle==270.0)
            return 1;
        else if (this.rotationAngle==90.0)
            return 2;
        else if (this.rotationAngle==180.0)
            return 3;
        else if (this.rotationAngle==0.0)
            return 4;
        else
            return 0;
    }



}
