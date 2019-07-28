package Arkanoid;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

import static Arkanoid.GameScene.*;

public class ArkanoidBall extends Circle {
    double ArkanoidBallInitialX=600;
    double ArkanoidBallInitialY=690;
    double ArkanoidBallX=ArkanoidBallInitialX;
    double ArkanoidBallY=ArkanoidBallInitialY;
    final double ArkanoidBallRadius=10;
    final double speedReductor=1; // For test purposes
    double Vx;
    double Vy;
    double ArkanoidBallSpeed=1; // These parameter sets speeed of Bar Movement

    ArkanoidBall(){

        //Setting initial parameters
        this.setRandomlyV();
        this.setCenterX(ArkanoidBallX);
        this.setCenterY(ArkanoidBallY);
        this.setRadius(ArkanoidBallRadius);
        this.setFill(Color.DARKVIOLET);
        //GameLayout.getChildren().add(this);






    }

     void setRandomlyV() {
        Random rand = new Random();
        do {
            this.Vx = (rand.nextInt(21) - 10)/speedReductor;
        }
        while (Vx == 0);
         do {
             this.Vy = (rand.nextInt(21) - 10)/speedReductor;
         }
         while (Vy >= 0);
    }

    public void moveArkanoidBall(ArkanoidBar ArkanoidBar){
        checkIfHitBorders();
        checkIfHitBar(ArkanoidBar);
        this.setCenterX(ArkanoidBallX=ArkanoidBallX+Vx);
        this.setCenterY(ArkanoidBallY=ArkanoidBallY+Vy);

    }

    public void setInitialPosition(){
        this.setCenterX(ArkanoidBallX=ArkanoidBallInitialX);
        this.setCenterY(ArkanoidBallY=ArkanoidBallInitialY);
        this.setRandomlyV();


    }

    void checkIfHitBorders(){
        if (ArkanoidBallY + Vy > (internalWindowHeight - (ArkanoidBallRadius + 1)))
           GameOver=true;
        if (ArkanoidBallX + Vx < (ArkanoidBallRadius + 1) || ArkanoidBallX + Vx > (internalWindowWidth - (ArkanoidBallRadius + 1)))
            this.Vx = -Vx;
        if (ArkanoidBallY + Vy < (ArkanoidBallRadius + 1) || ArkanoidBallY + Vy > (internalWindowHeight - (ArkanoidBallRadius + 1)))
            this.Vy = -Vy;
    }

    void checkIfHitBar(ArkanoidBar ArkanoidBar){
        if (    ArkanoidBallX + Vx > ArkanoidBar.ArkanoidBarX &&
                ArkanoidBallX + Vx < ArkanoidBar.ArkanoidBarX + ArkanoidBar.ArkanoidBarWidth &&
                (ArkanoidBallY + ArkanoidBallRadius + Vy > ArkanoidBar.ArkanoidBarY &&
                 ArkanoidBallY - ArkanoidBallRadius + Vy < ArkanoidBar.ArkanoidBarY + ArkanoidBar.ArkanoidBarHeight )||
                (ArkanoidBallY + ArkanoidBallRadius + Vy > ArkanoidBar.ArkanoidBarY + ArkanoidBar.ArkanoidBarHeight &&
                 ArkanoidBallY + ArkanoidBallRadius + Vy <  ArkanoidBar.ArkanoidBarY)) {
                this.Vy = -Vy;

        }


    }

    void checkIfHitWalls(ArrayList<ArkanoidWall> listOfWalls){
        for (int i=0; i<listOfWalls.size();i++){
            listOfWalls.get(i).checkIfBallHittedMe(this);
        }





    }





}
