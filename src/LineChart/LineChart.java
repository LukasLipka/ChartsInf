package LineChart;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import main.*;

public class LineChart {

    public List<Label> values = new ArrayList<>();

    public ChartField owner;

    public void render(GraphicsContext ctx){
        ctx.setLineWidth(5);

        double minBottomValue = Double.MAX_VALUE;
        double minLeftValue = Double.MAX_VALUE;
        double maxBottomValue = Double.MIN_VALUE;
        double maxLeftValue = Double.MIN_VALUE;

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

                ctx.strokeLine((currentBottomValue - minBottomValue) * bottomStep,
                        owner.getHeight() - (currentLeftValue - minLeftValue) * leftStep,
                        (nextBottomValue - minBottomValue) * bottomStep,
                        owner.getHeight() - (nextLeftValue - minLeftValue) * leftStep);

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

    public LineChart(ChartField owner) {
        this.owner = owner;
    }
}
