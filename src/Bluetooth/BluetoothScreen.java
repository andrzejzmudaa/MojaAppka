package Bluetooth;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BluetoothScreen{

    final static double WindowHeight = 764;
    final static double WindowWidth = 1200;
    static BorderPane WindowLayout = new BorderPane();
    public static Scene BluetoothScene = new Scene(WindowLayout,WindowHeight,WindowWidth);

    public static void DrawScene(Stage BluetoothStage){

        BluetoothStage.setMinHeight(WindowHeight);
        BluetoothStage.setMinWidth(WindowWidth);
        BluetoothStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        BluetoothStage.setScene(BluetoothScene);
        BluetoothStage.centerOnScreen();
        BluetoothStage.show();







    }
}
