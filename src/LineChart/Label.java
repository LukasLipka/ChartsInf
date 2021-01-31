package LineChart;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Label {

    public List<Integer> leftValues = new ArrayList<>();

    public List<Integer> bottomValues = new ArrayList<>();

    public Color labelColor;

    public LineChart owner;

    public Label(Color labelColor, LineChart owner,Integer bottomValues[],Integer leftValues[]) {
        this.leftValues = Arrays.asList(leftValues);
        this.bottomValues = Arrays.asList(bottomValues);
        this.labelColor = labelColor;
        this.owner = owner;
    }
}
