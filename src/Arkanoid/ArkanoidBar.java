package Arkanoid;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import static Arkanoid.GameScene.internalWindowHeight;
import static Arkanoid.GameScene.internalWindowWidth;


public class ArkanoidBar extends Rectangle {

    double ArkanoidBarX=500;
    double ArkanoidBarY=700;
    final double ArkanoidBarWidth=200;
    final double ArkanoidBarHeight=20;
    double ArkanoidBarSpeed=1; // These parameter sets speeed of Bar Movement

    ArkanoidBar(){
        //Setting initial parameters
        this.setX(ArkanoidBarX);
        this.setY(ArkanoidBarY);
        this.setWidth(ArkanoidBarWidth);
        this.setHeight(ArkanoidBarHeight);
        this.setArcWidth(10);
        this.setArcHeight(10);
        this.setFill(Color.FIREBRICK);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(5.0);
        //ds.setOffsetX(5.0);
        ds.setColor(Color.GRAY);
        //this.setEffect(ds);



    }

    void moveArkanoidBar(AnimatedArrow incoomingAnimatedArrow){
        incoomingAnimatedArrow.checkArrowColor();
        if (incoomingAnimatedArrow.checkArrowDirection()==1&&incoomingAnimatedArrow.isActive&&ArkanoidBarY-ArkanoidBarSpeed>0)
            this.setY(ArkanoidBarY=ArkanoidBarY - ArkanoidBarSpeed);
        else if (incoomingAnimatedArrow.checkArrowDirection()==2&&incoomingAnimatedArrow.isActive&&ArkanoidBarY+ArkanoidBarHeight+ArkanoidBarSpeed<internalWindowHeight)
            this.setY(ArkanoidBarY=ArkanoidBarY+ArkanoidBarSpeed);
        else if (incoomingAnimatedArrow.checkArrowDirection()==3&&incoomingAnimatedArrow.isActive&&ArkanoidBarX-ArkanoidBarSpeed>0)
            this.setX(ArkanoidBarX=ArkanoidBarX-ArkanoidBarSpeed);
        else if (incoomingAnimatedArrow.checkArrowDirection()==4&&incoomingAnimatedArrow.isActive&&ArkanoidBarX+ArkanoidBarWidth+ArkanoidBarSpeed<internalWindowWidth)
            this.setX(ArkanoidBarX=ArkanoidBarX+ArkanoidBarSpeed);


        //System.out.println("Pozycja BarX :"+ArkanoidBarX);
        System.out.println("Pozycja BarY :"+ArkanoidBarY);
    }



}
