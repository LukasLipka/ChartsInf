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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ChartField;
import main.ChartFieldProperties;
import main.Grid;
import main.GridProperties;
import LineChart.*;
import sun.rmi.server.InactiveGroupException;

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

    public String readRaw(Path path){
        try {
            return Files.readAllLines(path).toString().replace(",","\n").replace("[","").replace("]","").replace(" ","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void consumeRaw(String raw,GraphicsContext ctx){
        ChartField field = new ChartField(Integer.parseInt(readCommand(raw,"chart-field-start-x")),
                Integer.parseInt(readCommand(raw,"chart-field-start-y")),
                Integer.parseInt(readCommand(raw,"chart-field-end-x")),
                Integer.parseInt(readCommand(raw,"chart-field-end-y")),
                new ChartFieldProperties(
                        readCommand(raw, "chart-field-props-render-border").equals("true"),
                Integer.parseInt(readCommand(raw,"chart-field-props-border-width"))
                ,Color.rgb(Integer.parseInt(readCommand(raw,"chart-field-props-color-red")),
                Integer.parseInt(readCommand(raw,"chart-field-props-color-green")),
                Integer.parseInt(readCommand(raw,"chart-field-props-color-blue")),
                Double.parseDouble(readCommand(raw,"chart-field-props-color-opacity")))
        ));
        field.renderMe(ctx);
        Grid grid = new Grid(new GridProperties(Integer.parseInt(readCommand(raw,"grid-size")),

                Color.rgb(Integer.parseInt(readCommand(raw,"grid-props-color-red")),

                Integer.parseInt(readCommand(raw,"grid-props-color-green")),
                Integer.parseInt(readCommand(raw,"grid-props-color-blue")),
                Double.parseDouble(readCommand(raw,"grid-props-color-opacity"))),
                Integer.parseInt(readCommand(raw,"grid-props-grid-width"))),field);
        grid.renderMe(ctx);

        LineChart chart = new LineChart(field);

        while(raw.contains("label")) {
            String[] bottomValuess = readCommand(raw, "line-chart-label-bottom-values").split("\n");

            Integer[] bottomValues = new Integer[bottomValuess.length];
            int i = 0;
            for (String s : bottomValuess) {
                int g = Integer.parseInt(s.replace("\n","").trim());
                bottomValues[i] = g;
                i++;
            }

            String[] leftValuess = readCommand(raw, "line-chart-label-left-values").split("\n");

            Integer[] leftValues = new Integer[leftValuess.length];
            int x = 0;
            for (String s : leftValuess) {

                leftValues[x] = Integer.parseInt(s);
                x++;
            }


            Label label = new Label(Color.rgb(Integer.parseInt(readCommand(raw, "label-color-red")),
                    Integer.parseInt(readCommand(raw, "label-color-green")),
                    Integer.parseInt(readCommand(raw, "label-color-blue")),
                    Double.parseDouble(readCommand(raw, "label-color-opacity"))), chart,  bottomValues,leftValues);

            chart.values.add(label);
            if(raw.contains("label;")) {
                raw = raw.substring(raw.indexOf("label;") + 6);
            }else{
                break;
            }
            System.out.println(raw);
            chart.render(ctx);
        }

    }

    public String readCommand(String raw, String command){
        String newStr = raw.substring(raw.indexOf(command));
        newStr = newStr.substring(newStr.indexOf(":")+1,newStr.indexOf(";"));
        return newStr;
    }

}
