package main;

import LineChart.LineChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.ChartField;
import main.ChartFieldProperties;
import main.Grid;
import main.GridProperties;
import LineChart.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Canvas c = new Canvas(1000,1000);

        GraphicsContext ctx = c.getGraphicsContext2D();

        FlowPane root = new FlowPane(c);
        stage.setTitle("Grafy");
        stage.setScene(new Scene(root,1000, 1000));
        stage.show();

        ChartField chartField = new ChartField(5,5,955,955,new ChartFieldProperties(true, 10, Color.rgb(0,150,255,1)));
        chartField.renderMe(ctx);

         LineChart chart = new LineChart(chartField);

         chart.values.add(new Label(Color.rgb(10,10,10),chart,new Integer[]{10,20,30,40,50,60,70,80,90,100},new Integer[]{10,20,30,40,50,60,70,80,90,100}));

        Grid grid = new Grid(new GridProperties(20,Color.rgb(255,0,0,0.5),1),chartField);
        grid.renderMe(ctx);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
