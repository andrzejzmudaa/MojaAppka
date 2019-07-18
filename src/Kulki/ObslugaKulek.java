package Kulki;


import Kulki.DrawMainScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.util.Random;

public class ObslugaKulek {



    int promienkulki = 10;
    int MaxRozmiarOknaHeight = 0;
    int MaxRozmiarOknaWidth = 0;

    public ObslugaKulek(Pane MojPane, double maxRozmiarOknaHeight, double maxRozmiarOknaWidth) {

        this.MaxRozmiarOknaHeight = (int) maxRozmiarOknaHeight;
        this.MaxRozmiarOknaWidth = (int) maxRozmiarOknaWidth;
        System.out.println("Wywoluje konstruktor obslugi kulek");



        DrawMainScene.przycisk2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    kulka localkulka = new kulka();
                    MojPane.getChildren().add(localkulka);
                    System.out.println("Nastepny idex to "+ MojPane.getChildren().size());


                }


        });


    }




        public double SetInitialX () {
            Random rand = new Random();
            double result = (double) rand.nextInt(MaxRozmiarOknaWidth - 2 * (promienkulki + 1)) + promienkulki + 1;
            System.out.println("Wynik formuly " + result);
            return result;
        }


        public double SetInitialY () {
            Random rand = new Random();
            return (double) rand.nextInt(MaxRozmiarOknaHeight - 2 * (promienkulki + 1)) + promienkulki + 1;
        }


        public class kulka extends Circle {

            public int Vx = 0;
            public int Vy = 0;

            kulka() {
                super();
                this.setRadius(promienkulki);
                this.setCenterX(SetInitialX());
                this.setCenterY(SetInitialY());
                this.setFill(javafx.scene.paint.Color.RED);
                this.Vx = setRandomlyV();
                this.Vy = setRandomlyV();

            }

            int setRandomlyV() {
                int a;
                Random rand = new Random();
                do {
                    a = rand.nextInt(21) - 10;
                }
                while (a == 0);
                return a;


            }

            //Granice : promienkulki+1 do maxRozmiarOkna -(promienkulki+1)
            public void sprawdzPredkosc(double rozmiarOknaX, double rozmiarOknaY) {
                if (this.getCenterX() + Vx < (this.getRadius() + 1) || this.getCenterX() + Vx > (rozmiarOknaX - (this.getRadius() + 1)))
                    this.Vx = -Vx;
                if (this.getCenterY() + Vy < (this.getRadius() + 1) || this.getCenterY() + Vy > (rozmiarOknaX - (this.getRadius() + 1)))
                    this.Vy = -Vy;
                System.out.println("Sprawdzilem Predkosci");


            }






            }




    public static void pause(Thread watek,int czasPrzerwy) {
        try {
            watek.sleep(czasPrzerwy);
        } catch (InterruptedException e) {

        }
    }
    }









