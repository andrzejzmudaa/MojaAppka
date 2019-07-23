package Arkanoid;


import static Arkanoid.GameScene.GameWindowLayout;
import static Arkanoid.GameScene.arrowGroup;

public class GameThread implements Runnable {

    @Override
    public void run() {
        while(true) {

            while(GameScene.gameRunCommand){
                for(int i=0;i<arrowGroup.getChildren().size();i++)
                    ((AnimatedArrow)arrowGroup.getChildren().get(i)).checkArrowColor();

                //System.out.println("GameThread is running");







            }
        }
    }


    GameThread(){


    }

}
