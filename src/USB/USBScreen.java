package USB;

import Bluetooth.BluetoothHandling;
import Bluetooth.BluetoothScreen;
import PS3PAD.PS3PAD_USB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class USBScreen {
    final static double WindowHeight = 764;
    final static double WindowWidth = 800;
    static BorderPane WindowLayout = new BorderPane();
    public static Scene USBScene = new Scene(WindowLayout,WindowHeight,WindowWidth);
    public static Button searchButton = new Button("USB Device");
    public static Button closeButton = new Button("Close App");






    public static void DrawScene(Stage USBhStage) {


        PS3PAD_USB ps3 = new PS3PAD_USB();
        USBhStage.setMinHeight(WindowHeight);
        USBhStage.setMinWidth(WindowWidth);
        USBhStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        WindowLayout.setTop(searchButton);
        WindowLayout.setBottom(closeButton);

        USBhStage.setScene(USBScene);
        USBhStage.centerOnScreen();
        USBhStage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            ps3.getRawPS3PadData();
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Platform.exit();
            }
        });







    }





}
