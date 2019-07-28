package Arkanoid;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Arkanoid.GameScene.pointsScored;
import static Arkanoid.GameScene.score;

public class ArkanoidWall extends Rectangle {

    final double ArkanoidWallWidth=40;
    final double ArkanoidWallHeight=20;
    double ArkanoidWallInitialX;
    double ArkanoidWallInitialY;
    double ArkanoidWallX;
    double ArkanoidWallY;
    boolean isDestroyed= false;









    ArkanoidWall(double ArkanoidWallInitialX,double ArkanoidWallInitialY){
        this.ArkanoidWallInitialX = ArkanoidWallInitialX;
        this.ArkanoidWallInitialY = ArkanoidWallInitialY;
        this.setX(ArkanoidWallX=this.ArkanoidWallInitialX);
        this.setY(ArkanoidWallY=this.ArkanoidWallInitialY);
        this.setWidth(ArkanoidWallWidth);
        this.setHeight(ArkanoidWallHeight);
        this.setArcWidth(5);
        this.setArcHeight(5);
        this.setFill(Color.GREENYELLOW);



    }

    void checkIfBallHittedMe(ArkanoidBall Ball){
        if (isDestroyed)
            return;

        if (    Ball.ArkanoidBallX + Ball.Vx > ArkanoidWallX &&
                Ball.ArkanoidBallX + Ball.Vx < ArkanoidWallX + ArkanoidWallWidth &&
                (Ball.ArkanoidBallY + Ball.ArkanoidBallRadius + Ball.Vy > ArkanoidWallY &&
                        Ball.ArkanoidBallY - Ball.ArkanoidBallRadius + Ball.Vy < ArkanoidWallY + ArkanoidWallHeight )||
                (Ball.ArkanoidBallY + Ball.ArkanoidBallRadius + Ball.Vy > ArkanoidWallY + ArkanoidWallHeight &&
                        Ball.ArkanoidBallY + Ball.ArkanoidBallRadius + Ball.Vy <  ArkanoidWallY)) {
            Ball.Vy = - Ball.Vy;
            this.isDestroyed=true;
            this.setVisible(false);
            pointsScored++;




        }




    }

    void setInitialPosition(){
        this.setX(ArkanoidWallX=this.ArkanoidWallInitialX);
        this.setY(ArkanoidWallY=this.ArkanoidWallInitialY);
        isDestroyed= false;
        this.setVisible(true);



    }






}
