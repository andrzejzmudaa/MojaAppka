package Kulki;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class DrawMainScene {

    public static int a=0;
    volatile static boolean isRunning = false;
    final public static double RozmiarOknaKulekH = 400;
    final public static double RozmiarOknaKulekW = 400;
    final static double RozmiarOknaGlownegoH = 600;
    final static double RozmiarOknaGlownegoV = 600;
    public static Pane ukladwolny = new Pane();
    static BorderPane ukladgranic = new BorderPane();
    static Scene ScenaGlowna = new Scene(ukladgranic,RozmiarOknaGlownegoH,RozmiarOknaGlownegoV);
    public static HBox boxzprzyciskami = new HBox(20);
    public static Button przycisk1 = new Button("Start");
    public static Button przycisk2 = new Button("Dodaj kulke");
    ruchKulek ruchKulek = new ruchKulek(DrawMainScene.ukladwolny, DrawMainScene.RozmiarOknaKulekH, DrawMainScene.RozmiarOknaKulekW);
    public Thread watek = new Thread(ruchKulek);
    ObslugaKulek noweKulki = new ObslugaKulek(DrawMainScene.ukladwolny, DrawMainScene.RozmiarOknaKulekH, DrawMainScene.RozmiarOknaKulekW);

    DrawMainScene(){

        System.out.println("Konstruktor DrawMainScene");

            watek.setDaemon(true);
            watek.start();


    }

    public static void DrawScene(Stage StagePrzychodzaca){

        new DrawMainScene();


        //




        //Pane Declarations

        //Play View
        ukladgranic.setCenter(ukladwolny);
        ukladwolny.setPrefSize(RozmiarOknaKulekH,RozmiarOknaKulekW);
        ukladwolny.setMaxSize(RozmiarOknaKulekH,RozmiarOknaKulekW);
        ukladwolny.setMinSize(RozmiarOknaKulekH,RozmiarOknaKulekW);
        ukladwolny.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        // General View


        StagePrzychodzaca.setMinHeight(RozmiarOknaKulekH+100);
        StagePrzychodzaca.setMinWidth(RozmiarOknaKulekW+50);
        StagePrzychodzaca.setScene(ScenaGlowna);


        boxzprzyciskami.getChildren().add(przycisk1);
        boxzprzyciskami.getChildren().add(przycisk2);
        ukladgranic.setBottom(boxzprzyciskami);
        boxzprzyciskami.setAlignment(Pos.CENTER);
        StagePrzychodzaca.show();
        System.out.println(ukladwolny.getBoundsInLocal().getMinX());
        System.out.println(ukladwolny.getBoundsInLocal().getMinY());
        System.out.println(ukladwolny.getBoundsInLocal().getMaxX());
        System.out.println(ukladwolny.getBoundsInLocal().getMaxY());
        //Wybieranie Sceny w srodku





         DrawMainScene.przycisk1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
             System.out.println("Obsluga kulek....");
             isRunning=!isRunning;
             System.out.println("isRunning status to "+isRunning);
                    //System.out.println("Status: "+ DrawMainScene.watek.isAlive());




                }


            });








    }







    class ruchKulek implements Runnable{

        ruchKulek(Pane MojPane, double maxRozmiarOknaHeight, double maxRozmiarOknaWidth){
            this.MojPane = MojPane;
            this.maxRozmiarOknaHeight = maxRozmiarOknaHeight;
            this.maxRozmiarOknaWidth = maxRozmiarOknaWidth;
        }

        Pane MojPane;
        double maxRozmiarOknaHeight;
        double maxRozmiarOknaWidth;

        volatile boolean initalWatekstart=false;

        @Override
        public void run() {


            while(true){
                while(isRunning) {
                    System.out.println("Dzialam.........");
                    if (MojPane.getChildren().isEmpty())
                        System.out.println("Pusty");

                    try {
                        Thread.sleep(100);
                        //wait(100);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    //ObslugaKulek.pause(ruchKulek,1000);
                    for (int i = 0; i < MojPane.getChildren().size(); i++) {
                        ObslugaKulek.kulka tempkulka = (ObslugaKulek.kulka) MojPane.getChildren().get(i);
                        tempkulka.sprawdzPredkosc(maxRozmiarOknaHeight, maxRozmiarOknaWidth);
                        tempkulka.setCenterX(tempkulka.getCenterX() + tempkulka.Vx);
                        tempkulka.setCenterY(tempkulka.getCenterY() + tempkulka.Vy);
                        System.out.println("Lece....");
                    }
                }


            }}






    }




}

