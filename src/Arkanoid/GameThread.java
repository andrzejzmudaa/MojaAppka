package Arkanoid;

import static Arkanoid.GameScene.*;

public class GameThread implements Runnable {
    public ArkanoidBar ArkanoidBar = new ArkanoidBar();
    public ArkanoidBall ArkanoidBall = new ArkanoidBall();
    public volatile boolean setObjectsInitialPosition=false;


    @Override
    public void run() {
        System.out.println("Odpalam Watek");

        while(true) {
            if (setObjectsInitialPosition){
                ArkanoidBall.setInitialPosition();
                ArkanoidBar.setInitialPosition();
                setObjectsInitialPosition=false;
            }

            while(GameScene.gameRunCommand){

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<arrowGroup.getChildren().size();i++){
                    ArkanoidBar.moveArkanoidBar(((AnimatedArrow)arrowGroup.getChildren().get(i)));
                }
                ArkanoidBall.moveArkanoidBall(ArkanoidBar);






                //System.out.println(ArkanoidBall.setRandomlyV());







            }
        }
    }




    GameThread(){
        //Creating ArkanoidBar obcject
        GameLayout.getChildren().add(ArkanoidBar);
        GameLayout.getChildren().add(ArkanoidBall);



    }

}
