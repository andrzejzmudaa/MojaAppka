package USB;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;

import static USB.USBScreen.ps3PAD;

public class PositionCircle extends Circle{

    double InitialX;
    double InitialY;
    public Circle bounds;


    PositionCircle(double InitialX,double InitialY,double Radius){
        this.setCenterX(this.InitialX=InitialX);
        this.setCenterY(this.InitialY=InitialY);
        this.setRadius(Radius);
        this.setFill(Color.RED);
        bounds = new Circle(InitialX,InitialY,128,Color.TRANSPARENT);
        bounds.setStroke(Color.BLACK);
    }


    public void setPositionXY(byte x, byte y){
        this.setCenterX((InitialX-127)+(double)(x & 0xFF));
        this.setCenterY((InitialY-127)+(double)(y & 0xFF));
    }
}
