package Arkanoid;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

import static Arkanoid.GameScene.*;

public class GameThread implements Runnable {
    public ArkanoidBar ArkanoidBar = new ArkanoidBar();
    public ArkanoidBall ArkanoidBall = new ArkanoidBall();
    public ArrayList<ArkanoidWall> listOfObstacles = new ArrayList();
    public volatile boolean setObjectsInitialPosition = false;
    AnimationTimer animator = new AnimationTimer() {
        @Override
        public void handle(long l) {



            if (setObjectsInitialPosition)
                setInitialPosition();



            if (GameScene.gameRunCommand) {



                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < arrowGroup.getChildren().size(); i++) {
                    ArkanoidBar.moveArkanoidBar(((AnimatedArrow) arrowGroup.getChildren().get(i)));
                }
                ArkanoidBall.moveArkanoidBall(ArkanoidBar);
                ArkanoidBall.checkIfHitWalls(listOfObstacles);




            }
            if (GameOver) {
                setInitialPosition();
                gameRunCommand=false;
            }
            score.setText(Integer.toString(pointsScored));
            GameOverLabel.setVisible(GameOver);

        }
    };


    @Override
    public void run() {
        System.out.println("Odpalam Watek");
        animator.start();

    }


    GameThread() {
        //Creating Obstacles
        createObstacles(15,5);
        //Creating ArkanoidBar obcject
        GameLayout.getChildren().add(ArkanoidBar);
        GameLayout.getChildren().add(ArkanoidBall);
        for (int i = 0; i < listOfObstacles.size(); i++)
            GameLayout.getChildren().add(listOfObstacles.get(i));


    }


    void createObstacles(int rowsX, int rowsY) {
        int i;
        int j;
        int startingX=200;
        int startingY=200;
        for (j = 0; j < rowsY; j++) {
            for (i = 0; i <= rowsX; i++) {
                listOfObstacles.add(new ArkanoidWall(startingX+i*50,startingY+j*30));

            }
        }
    }

    void setInitialPosition(){

        for (int i = 0; i < listOfObstacles.size(); i++)
            listOfObstacles.get(i).setInitialPosition();

        ArkanoidBall.setInitialPosition();
        ArkanoidBar.setInitialPosition();
        setObjectsInitialPosition = false;



    }
}

