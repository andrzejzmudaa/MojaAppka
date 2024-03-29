package RysowanieEkranu;

import Arkanoid.GameScene;
import Bluetooth.BluetoothHandling;
import Bluetooth.BluetoothScreen;
import Kulki.DrawMainScene;
import USB.USBScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuGlowne extends Application {

    final static double windowHeight=800;
    final static double windowWidth=600;
    static BorderPane Layout = new BorderPane();
    public static Button przyciskUruchom = new Button("Uruchom");
    final ComboBox<String> przyciskWyboru = new ComboBox<String>();
    static Scene MenuGlowne = new Scene(Layout,windowHeight,windowWidth);
    public static HBox boxzprzyciskami = new HBox(20);


    @Override
    public void start(Stage stage) throws Exception {
        przyciskWyboru.getItems().addAll("Kulki", "Arkanoid", "Bluetooth test","USB App");
        stage.setMinHeight(800);
        stage.setMinWidth(600);

        boxzprzyciskami.getChildren().add(przyciskUruchom);
        boxzprzyciskami.setAlignment(Pos.CENTER);
        Layout.setCenter(przyciskWyboru);
        Layout.setBottom(boxzprzyciskami);


        stage.setScene(MenuGlowne);

        //Temporary to skip choosing
        //GameScene.DrawScene(stage);

        stage.show();
        USBScreen.DrawScene(stage);
        przyciskUruchom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String wybranyEkran = przyciskWyboru.getValue();
                if (wybranyEkran.equals(przyciskWyboru.getItems().get(0)))
                    DrawMainScene.DrawScene(stage);
                else if (wybranyEkran.equals(przyciskWyboru.getItems().get(1)))
                    GameScene.DrawScene(stage);
                else if (wybranyEkran.equals(przyciskWyboru.getItems().get(2)))
                    BluetoothScreen.DrawScene(stage);
                else if (wybranyEkran.equals(przyciskWyboru.getItems().get(3)))
                    USBScreen.DrawScene(stage);

            }
        });







    }
}
