package Arkanoid;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class GameScene {
    //Internal window dimenssions and Layout
    public static Pane GameLayout = new Pane();
    final static double internalWindowHeight = 764;
    final static double internalWindowWidth = 1200;
    //External window dimenssions and Layout
    static BorderPane GameWindowLayout = new BorderPane();
    final static double externalWindowHeight = internalWindowHeight+200;
    final static double externalWindowWidth = internalWindowWidth+100;
    //Start Button
    public static volatile boolean gameRunCommand=false;
    public static Button buttonStart = new Button("Start");
    public static Button buttonReset = new Button("Reset");
    public static HBox buttonStartGroup = new HBox(20);
    // Score label
    public static int pointsScored=0;
    public static Label result = new Label("Result: ");
    public static Label score = new Label("0");
    public static HBox scoreLabelGroup = new HBox(20,result,score);
    //AnimatedArrows
    final static double arrowMargins=20;
    final static double arrowScale=10;
    public static Scene gameScene = new Scene(GameWindowLayout,externalWindowHeight,externalWindowWidth);
    static AnimatedArrow arrowUP = new AnimatedArrow(arrowScale,270, KeyCode.UP);
    static AnimatedArrow arrowDOWN = new AnimatedArrow(arrowScale,90, KeyCode.DOWN);
    static AnimatedArrow arrowLEFT = new AnimatedArrow(arrowScale,180, KeyCode.LEFT);
    static AnimatedArrow arrowRIGHT = new AnimatedArrow(arrowScale,0, KeyCode.RIGHT);
    public static HBox arrowGroup = new HBox(30,arrowUP,arrowDOWN,arrowLEFT,arrowRIGHT);
    //Both added
    public static HBox TopHbox = new HBox(scoreLabelGroup,arrowGroup);
    //GameOver
    public static boolean GameOver= false;
    public static Label GameOverLabel = new Label("Game Over");
    //Game Scene

    //Constructor of GameScene class
    //These function will contain all functions running
    GameScene(){
        GameThread GameThread = new GameThread();
        Thread GameRunning = new Thread(GameThread);

        GameScene.buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameRunCommand=!gameRunCommand;
                if (gameRunCommand){
                    GameOver=false;
                    buttonStart.setText("Stop");
                    pointsScored=0;
                }
                else
                    buttonStart.setText("Start");
                GameWindowLayout.setFocusTraversable(false);
                GameWindowLayout.requestFocus();
            }
        });

        GameScene.buttonReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameRunCommand)
                    gameRunCommand=!gameRunCommand;
                buttonStart.setText("Start");
                GameThread.setObjectsInitialPosition=true;
            }
        });

        GameRunning.start();








    }



    public static void DrawScene(Stage GameStage){

        //Setting external window Size
        GameStage.setMinHeight(externalWindowHeight);
        GameStage.setMinWidth(externalWindowWidth);
        //Setting Start button
        buttonStart.setPrefSize(200,50);
        buttonReset.setPrefSize(200,50);
        buttonStartGroup.getChildren().add(buttonStart);
        buttonStartGroup.getChildren().add(buttonReset);
        buttonStartGroup.setAlignment(Pos.CENTER);
        buttonStartGroup.setPadding(new Insets(10,10,10,10));
        GameWindowLayout.setBottom(buttonStartGroup);
        //buttonStart.add
        //Setting Play view
        GameWindowLayout.setCenter(GameLayout);
        GameLayout.setPrefSize(internalWindowWidth,internalWindowHeight);
        GameLayout.setMaxSize(internalWindowWidth,internalWindowHeight);
        GameLayout.setMinSize(internalWindowWidth,internalWindowHeight);
        BackgroundImage backgroundimage = new BackgroundImage(new Image("Arkanoid/img/sky.bmp"),null,null,null,null);
        GameLayout.setBackground(new Background(backgroundimage));
        GameLayout.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //Setting label and Arrows
        result.setFont(Font.font(32));
        score.setFont(Font.font(32));
        scoreLabelGroup.setAlignment(Pos.TOP_LEFT);
        arrowGroup.setAlignment(Pos.TOP_RIGHT);
        GameWindowLayout.setTop(TopHbox);
        TopHbox.setAlignment(Pos.CENTER);
        scoreLabelGroup.setPadding(new Insets(arrowMargins,arrowMargins,arrowMargins,arrowMargins));
        arrowGroup.setPadding(new Insets(arrowMargins,arrowMargins,arrowMargins,arrowMargins));
        GameOverLabel.setFont(Font.font(72));
        GameOverLabel.setTextFill(Color.RED);
        System.out.println("GameOver width: "+GameOverLabel.getWidth());
        GameOverLabel.setLayoutX((internalWindowWidth/2)-200);
        GameOverLabel.setLayoutY(internalWindowHeight/2-GameOverLabel.getHeight());
        GameLayout.getChildren().add(GameOverLabel);





        GameWindowLayout.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //System.out.println("Key Code pressed: "+event.getEventType());
                switch (event.getCode()) {
                    case UP:    arrowUP.isActive = true; event.consume(); break;
                    case DOWN:  arrowDOWN.isActive = true; event.consume(); break;
                    case LEFT:  arrowLEFT.isActive  = true; event.consume(); break;
                    case RIGHT: arrowRIGHT.isActive  = true; event.consume(); break;

                }
            }
        });







        GameWindowLayout.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //System.out.println("Key Code released: "+event.getEventType());
                switch (event.getCode()) {
                    case UP:    arrowUP.isActive = false; break;
                    case DOWN:  arrowDOWN.isActive = false; break;
                    case LEFT:  arrowLEFT.isActive  = false; break;
                    case RIGHT: arrowRIGHT.isActive  = false; break;

                }
            }
        });




        //Test

        //buttonStartGroup.setBackground(new Background(new BackgroundFill(Color.AQUA,null,null)));
        GameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        GameStage.setScene(gameScene);
        GameStage.centerOnScreen();

        GameStage.show();
        //Create instance of GameScene
        new GameScene();






        System.out.println("Odpalilem GameScene");









    }





}
