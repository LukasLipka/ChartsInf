package main;

import LineChart.LineChart;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ChartField;
import main.ChartFieldProperties;
import main.Grid;
import main.GridProperties;
import LineChart.*;
import sun.rmi.server.InactiveGroupException;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Canvas c = new Canvas(1000,1000);

        GraphicsContext ctx = c.getGraphicsContext2D();

       // showMainScreen(ctx);

        FlowPane root = new FlowPane(c);
        stage.setTitle("Grafy");
        stage.setScene(new Scene(root,1000, 1000));
        stage.show();

        c.setOnMouseClicked(event -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".chart files","*.chart","*.CHART"));
            chooser.setTitle("Choose your .chart file");
            File file = chooser.showOpenDialog(stage);
            Path path = Paths.get(file.getAbsolutePath());
            ctx.clearRect(0,0,1000,1000);
            consumeRaw(readRaw(path),ctx);
        });


        //ChartField chartField = new ChartField(5,5,955,955,new ChartFieldProperties(true, 10, Color.rgb(0,150,255,1)));
       // chartField.renderMe(ctx);

       // LineChart chart = new LineChart(chartField);

      //  chart.values.add(new Label(Color.rgb(10,10,10),chart,new Integer[]{10,20,30,40,50,60,70,80,90,100},new Integer[]{50,7,98,74,55,41,78,89,90,41}));
      //  chart.values.add(new Label(Color.RED,chart,new Integer[]{10,20,30,40,50,60,70,80,90,100}, new Integer[]{1,42,468,75,860,458,75,12,35,45} ));
      //  chart.render(ctx);
    //    Grid grid = new Grid(new GridProperties(20,Color.rgb(255,0,0,0.5),1),chartField);
     //   grid.renderMe(ctx);

    }

    public void showMainScreen(GraphicsContext ctx) {
        ctx.setFont(Font.font(200));
        ctx.strokeText("Charts",225,150);
        ctx.strokeRect(500,250,250,100);
        ctx.strokeText("Load",750,250);
        ctx.strokeRect(100,250,250,100);
    }

    public String readRaw(Path path){
        try {
            return Files.readAllLines(path).toString().replace(",","\n").replace("[","").replace("]","").replace(" ","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void consumeRaw(String raw,GraphicsContext ctx){
        String rawInst = raw;
        ChartField field = new ChartField(Integer.parseInt(readCommand(rawInst,"chart-field-start-x")),
                Integer.parseInt(readCommand(rawInst,"chart-field-start-y")),
                Integer.parseInt(readCommand(rawInst,"chart-field-end-x")),
                Integer.parseInt(readCommand(rawInst,"chart-field-end-y")),
                new ChartFieldProperties(
                        readCommand(rawInst, "chart-field-props-render-border").equals("true"),
                Integer.parseInt(readCommand(rawInst,"chart-field-props-border-width"))
                ,Color.rgb(Integer.parseInt(readCommand(rawInst,"chart-field-props-color-red")),
                Integer.parseInt(readCommand(rawInst,"chart-field-props-color-green")),
                Integer.parseInt(readCommand(rawInst,"chart-field-props-color-blue")),
                Double.parseDouble(readCommand(rawInst,"chart-field-props-color-opacity")))
        ));
        field.renderMe(ctx);
        Grid grid = new Grid(new GridProperties(Integer.parseInt(readCommand(rawInst,"grid-size")),

                Color.rgb(Integer.parseInt(readCommand(rawInst,"grid-props-color-red")),

                Integer.parseInt(readCommand(rawInst,"grid-props-color-green")),
                Integer.parseInt(readCommand(rawInst,"grid-props-color-blue")),
                Double.parseDouble(readCommand(rawInst,"grid-props-color-opacity"))),
                Integer.parseInt(readCommand(rawInst,"grid-props-grid-width"))),field);
        grid.renderMe(ctx);

        LineChart chart = new LineChart(field,new LineChartProperties());
        int test = 0;
        //raw = raw.substring(raw.indexOf("label;") +6);
        while(rawInst.contains("label;")) {
            test++;
            String[] bottomValuess = readCommand(rawInst, "line-chart-label-bottom-values").split("\n");

            Integer[] bottomValues = new Integer[bottomValuess.length];
            int i = 0;
            for (String s : bottomValuess) {
                int g = Integer.parseInt(s.replace("\n","").trim());
                bottomValues[i] = g;
                i++;
            }

            String[] leftValuess = readCommand(rawInst, "line-chart-label-left-values").split("\n");

            Integer[] leftValues = new Integer[leftValuess.length];
            int x = 0;
            for (String s : leftValuess) {

                leftValues[x] = Integer.parseInt(s);
                x++;
            }

            Label label = new Label(Color.rgb(Integer.parseInt(readCommand(rawInst, "label-color-red")),
                    Integer.parseInt(readCommand(rawInst, "label-color-green")),
                    Integer.parseInt(readCommand(rawInst, "label-color-blue")),
                    Double.parseDouble(readCommand(rawInst, "label-color-opacity"))), chart,  bottomValues,leftValues);

            chart.values.add(label);
            if(rawInst.contains("label;")) {
                rawInst = rawInst.substring(rawInst.indexOf("label;") +6);

            }else{
                break;
            }
            chart.render(ctx);
        }

    }

    public String readCommand(String raw, String command){
        String newStr = raw.substring(raw.indexOf(command));
        newStr = newStr.substring(newStr.indexOf(":")+1,newStr.indexOf(";"));
        return newStr;
    }

}
