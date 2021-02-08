package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {

    GridProperties minorGridProps;

    GridProperties majorGridProps;

    ChartField fieldIn;



    public Grid(GridProperties minorGridProps, ChartField fieldIn) {
        this.minorGridProps = minorGridProps;
        this.fieldIn = fieldIn;
    }

    public void renderMe(GraphicsContext ctx){
        ctx.setStroke(minorGridProps.gridColor);
        ctx.setLineWidth(minorGridProps.gridWidth);
        for(int i = fieldIn.getStartX();i<= fieldIn.getEndX() - minorGridProps.gridSize*2;i+= minorGridProps.gridSize){
            ctx.strokeLine(fieldIn.getStartX() + i, fieldIn.getStartY(), i + fieldIn.getStartX(), fieldIn.getEndY()); // horizontal lines
        }

        for(int i = fieldIn.getStartY();i<= fieldIn.getEndY() - minorGridProps.gridSize*2;i+= minorGridProps.gridSize){
            ctx.strokeLine(fieldIn.getStartX(), fieldIn.getStartY() + i,fieldIn.getEndX(), fieldIn.getStartY()+i);  // vertical lines
        }
        ctx.setFill(Color.rgb(0,0,0,1));
    }


}

