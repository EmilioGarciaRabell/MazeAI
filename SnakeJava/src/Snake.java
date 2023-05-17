package SnakeJava.src;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

import java.util.Observable;
import java.util.Observer;

public class Snake extends Application implements Observer {

    private int width;
    private int height;


    @Override
    public void init() {
        // Initialization code here
        this.width = 600;
        this.height = 600;
    }

    @Override
    public void start(Stage primaryStage) {
        // Game UI setup code here

        // Create the root layout
        BorderPane root = new BorderPane();

        // Create a scene
        Scene scene = new Scene(root, width, height);

        // Set the scene on the stage
        primaryStage.setScene(scene);


        Text score = new Text("points" + 0);
        score.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        score.setFill(Color.web("#4E4E4E"));

        FlowPane topMsgs = new FlowPane(score);

        root.setTop(topMsgs);

        Canvas gameCanvas = new Canvas(600, 580);

        // Create a rounded rectangle shape on the Canvas
        double roundness = 10; // Adjust the roundness as needed
        double canvasWidth = gameCanvas.getWidth();
        double canvasHeight = gameCanvas.getHeight();
        double boxWidth = canvasWidth - 40; // Adjust the box size as needed
        double boxHeight = canvasHeight - 40;
        double boxX = (canvasWidth - boxWidth) / 2;
        double boxY = (canvasHeight - boxHeight) / 2;
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY); // Adjust the color as needed
        gc.fillRoundRect(boxX, boxY, boxWidth, boxHeight, roundness, roundness);

        // Set the Canvas in the center of the BorderPane
        root.setCenter(gameCanvas);
        BorderPane.setAlignment(gameCanvas, Pos.CENTER);




        // Show the stage
        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    public static void main(String[] args) {
        launch(args);
    }


}
