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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class USBScreen {
    final static double WindowHeight = 764;
    final static double WindowWidth = 800;
    static BorderPane WindowLayout = new BorderPane();
    public static Scene USBScene = new Scene(WindowLayout,WindowHeight,WindowWidth);
    public static Button searchButton = new Button("USB Device");
    public static Button closeButton = new Button("Close App");
    public static Pane centerPane = new Pane();
    static PS3PAD_USB ps3PAD = new PS3PAD_USB();
    Thread ps3Handling = new Thread(ps3PAD);
    static PositionCircle LAnalog = new PositionCircle(200,200,5);
    static PositionCircle RAnalog = new PositionCircle(600,200,5);
    Thread lAnalogHandling = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                LAnalog.setPositionXY(ps3PAD.LANALOG.buttonLeftRightValue, ps3PAD.LANALOG.buttonUpDownValue);
                RAnalog.setPositionXY(ps3PAD.RANALOG.buttonLeftRightValue, ps3PAD.RANALOG.buttonUpDownValue);
            }
        }
    });


    public static void DrawScene(Stage USBhStage) {

        USBScreen screen = new USBScreen();



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


        centerPane.getChildren().add(LAnalog);
        centerPane.getChildren().add(LAnalog.bounds);
        centerPane.getChildren().add(RAnalog);
        centerPane.getChildren().add(RAnalog.bounds);
        WindowLayout.setCenter(centerPane);

        USBhStage.setScene(USBScene);
        USBhStage.centerOnScreen();
        USBhStage.show();


        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //PositionCircle.setPositionXY(ps3PAD.LANALOG.buttonLeftRightValue,ps3PAD.LANALOG.buttonUpDownValue);

            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ps3PAD.disconnectPad();

                Platform.exit();
            }
        });







    }

    USBScreen(){
        ps3Handling.start();
        lAnalogHandling.start();

    }





}
