package Bluetooth;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.bluetooth.BluetoothStateException;

public class BluetoothScreen{

    final static double WindowHeight = 764;
    final static double WindowWidth = 800;
    static BorderPane WindowLayout = new BorderPane();
    public static Scene BluetoothScene = new Scene(WindowLayout,WindowHeight,WindowWidth);
    public static Button searchButton = new Button("Search Device");






    public static void DrawScene(Stage BluetoothStage) {

        new BluetoothScreen();

        BluetoothStage.setMinHeight(WindowHeight);
        BluetoothStage.setMinWidth(WindowWidth);
        BluetoothStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        WindowLayout.setTop(searchButton);

        BluetoothStage.setScene(BluetoothScene);
        BluetoothStage.centerOnScreen();
        BluetoothStage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        Thread MyBluetoothThread = new Thread(new BluetoothHandling());
        MyBluetoothThread.start();



    }





}
