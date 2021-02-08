package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.PortUnreachableException;
import java.nio.file.Path;

public class ChartField {

    private int startX;
    private int endX;

    private int startY;
    private int endY;

    private String name;

    ChartFieldProperties properties;

    public ChartField(int startX,int startY, int endX, int endY,  String name,ChartFieldProperties properties) {
        this.name = name;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.properties = properties;
    }

    public void renderMe(GraphicsContext ctx){
        ctx.setStroke(properties.borderColor);
        ctx.setLineWidth(properties.borderWidth);
        ctx.strokeRect(this.startX,this.startY,getWidth() - startX,getHeight() - startY);
        ctx.setFont(Font.font("Arial",50));
        ctx.fillText(name,this.startX + getWidth()/2 - (name.length()/2)*50 ,this.startY + 50);
        ctx.stroke();
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getWidth(){
        return this.endX - this.startX;
    }

    public int getHeight(){
        return this.endX - this.startX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
