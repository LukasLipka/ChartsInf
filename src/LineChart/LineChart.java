package LineChart;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import main.*;

public class LineChart {

    public List<Label> values = new ArrayList<>();

    public ChartField owner;

    public void render(GraphicsContext ctx){
        for (Label currentLabel : values) {
            int leftValuePerPixel = getMaxValueOfList(currentLabel.leftValues) / owner.getHeight();
            int bottomValuePerPixel = getMaxValueOfList(currentLabel.bottomValues) / owner.getWidth();
            System.out.println("left: " + leftValuePerPixel + " bottom: " + bottomValuePerPixel);
        }
    }

    public int getMaxValueOfList(List<Integer> list){
        int maxValue = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i)>maxValue){
                maxValue = list.get(i);
            }

        }
        return maxValue;
    }

    public LineChart(ChartField owner) {
        this.owner = owner;
    }
}
