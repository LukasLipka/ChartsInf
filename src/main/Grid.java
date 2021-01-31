package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.ChartField;

public class Grid {

    GridProperties properties;

    ChartField fieldIn;

    public Grid(GridProperties properties, ChartField fieldIn) {
        this.properties = properties;
        this.fieldIn = fieldIn;
    }

    public void renderMe(GraphicsContext ctx){
        ctx.setStroke(properties.gridColor);
        ctx.setLineWidth(properties.gridWidth);
        for(int i = fieldIn.getStartX();i< fieldIn.getEndX();i+= properties.gridSize){
            ctx.strokeLine(i, fieldIn.getStartY(), i, fieldIn.getEndY()); // horizontal lines
        }

        for(int i = fieldIn.getStartY();i< fieldIn.getEndY();i+= properties.gridSize){
            ctx.strokeLine(fieldIn.getStartX(), fieldIn.getStartY() + i,fieldIn.getEndX(), fieldIn.getStartY()+i);  // vertical lines
        }
        ctx.setFill(Color.rgb(0,0,0,1));
    }


}

