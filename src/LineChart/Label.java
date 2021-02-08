package LineChart;

import javafx.scene.paint.Color;

import java.util.*;

public class Label {

    public List<Integer> leftValues = new ArrayList<>();

    public List<Integer> bottomValues = new ArrayList<>();

    public Color labelColor;

    public LineChart owner;

    public String labelName;

    public Label(Color labelColor, LineChart owner,String labelName,Integer[] bottomValues,Integer[] leftValues) {
        this.leftValues.addAll(Arrays.asList(leftValues));
        this.labelName = labelName;
        this.bottomValues.addAll(Arrays.asList(bottomValues));
        this.labelColor = labelColor;
        this.owner = owner;
    }
}
