package Arkanoid;


import java.util.Date;

import static Arkanoid.GameScene.*;

public class GameThread implements Runnable {

    ArkanoidBar ArkanoidBar = new ArkanoidBar();

    @Override
    public void run() {
        long time=System.nanoTime();
        while(true) {
            while(GameScene.gameRunCommand){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<arrowGroup.getChildren().size();i++){
                    ArkanoidBar.moveArkanoidBar(((AnimatedArrow)arrowGroup.getChildren().get(i)));
                }






                //System.out.println("GameThread is running");







            }
        }
    }


    GameThread(){
        //Creating ArkanoidBar obcject
        GameLayout.getChildren().add(ArkanoidBar);


    }

}
