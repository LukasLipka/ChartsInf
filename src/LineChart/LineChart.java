package LineChart;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class LineChart {

    public List<Label> values = new ArrayList<>();

    public ChartField owner;

    public LineChartProperties properties;

    double minBottomValue = Double.MAX_VALUE;
    double minLeftValue = Double.MAX_VALUE;
    double maxBottomValue = Double.MIN_VALUE;
    double maxLeftValue = Double.MIN_VALUE;

    public void render(GraphicsContext ctx){
        //rendering lines and series
        ctx.setLineWidth(5);



        for (Label currentLabel : values) {
            minBottomValue = Math.min(minBottomValue, getMinValueOfList(currentLabel.bottomValues));
            minLeftValue = Math.min(minLeftValue, getMinValueOfList(currentLabel.leftValues));
            maxBottomValue = Math.max(maxBottomValue, getMaxValueOfList(currentLabel.bottomValues));
            maxLeftValue = Math.max(maxLeftValue, getMaxValueOfList(currentLabel.leftValues));
        }

        double bottomRange = maxBottomValue - minBottomValue;
        double leftRange = maxLeftValue - minLeftValue;

        double bottomStep = owner.getWidth() / bottomRange;
        double leftStep = owner.getHeight() / leftRange;

        for (Label currentLabel : values) {
            ctx.setStroke(currentLabel.labelColor);
            System.out.println(owner.getHeight());

            System.out.println(currentLabel.bottomValues.size());

            for(int iteration = 0; iteration < currentLabel.leftValues.size()-1; iteration++){

                double currentBottomValue = currentLabel.bottomValues.get(iteration);
                double currentLeftValue = currentLabel.leftValues.get(iteration);
                double nextBottomValue = currentLabel.bottomValues.get(iteration + 1);
                double nextLeftValue = currentLabel.leftValues.get(iteration + 1);

                ctx.strokeLine((currentBottomValue - minBottomValue) * bottomStep + owner.getStartX(),
                        owner.getHeight() - (currentLeftValue - minLeftValue) * leftStep + owner.getStartX(),
                        (nextBottomValue - minBottomValue) * bottomStep  + owner.getStartX(),
                        owner.getHeight() - (nextLeftValue - minLeftValue) * leftStep + owner.getStartY());

            }
        }
        // rendering legend
        System.out.println(properties.showLegend+" not");
        if(properties.showLegend){
            ctx.setFont(properties.fontUsed);
            int x;
            int y;
            switch (properties.legendOrientation) {
                case TOP:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 50 - 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 12;
                    break;
                case BOTTOM:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 50 - 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 88;
                    break;
                case RIGHT:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 75;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 50;
                    break;
                case LEFT:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 25 - 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 50;
                    break;
                case RIGHT_BOTTOM_CORNER:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 97 - 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 95;
                    break;
                case RIGHT_TOP_CORNER:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 97 - 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 5;
                    break;
                case LEFT_BOTTOM_CORNER:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 3 + 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 95;
                    break;
                case LEFT_TOP_CORNER:
                    x = owner.getStartX() + (owner.getWidth() / 100) * 3 + 150;
                    y = owner.getStartY() + (owner.getHeight() / 100) * 5;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + properties.legendOrientation);
            }
            int offset = 0;
            ctx.setFont(Font.font(properties.fontUsed.getFamily(),50));
            ctx.fillText("Legend",x-20,y-40);
            ctx.setFont(properties.fontUsed);
            for(Label label:values){
                Color labelColor = label.labelColor;
                ctx.setFill(labelColor);
                ctx.fillRect(x,y+offset,10,10);
                ctx.setFill(Color.BLACK);
                ctx.fillText(label.labelName, x + 15,y + offset + properties.fontUsed.getSize()/2);
                ctx.fill();
                offset +=20;
            }

        }
    }

    public int getMaxValueOfList(List<Integer> list){
        int maxValue = 0;
        for(int value : list){
            if(value>maxValue){
                maxValue = value;
            }
        }
        return maxValue;
    }
    public int getMinValueOfList(List<Integer> list){
        int minValue = 2100000000;
        for (int value : list) {
            if (value < minValue) {
                minValue = value;
            }

        }
        return minValue;
    }

    public LineChart(ChartField owner, LineChartProperties properties) {
        this.owner = owner;
        this.properties = properties;
        this.properties.owner = this;
    }
    public void addProperties(LineChartProperties properties){
        this.properties = properties;
        this.properties.owner = this;
    }

    public void renderGridilness(GraphicsContext ctx) {
        if(properties.showBottomValues){
            ctx.setFont(properties.fontUsed);
            System.out.println(maxBottomValue);
            for(int i = 0;i<=maxBottomValue;i+= properties.bottomValuesLabelStep){
                ctx.fillText(String.valueOf(i),owner.getStartX()+i * properties.bottomValuesLabelStep,owner.getEndY()+30);
            }
        }
        properties.showBottomValues = false;
    }
}
