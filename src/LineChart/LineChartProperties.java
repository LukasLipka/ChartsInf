package LineChart;

import javafx.scene.text.Font;
import main.Properties;
import misc.Orientation;

import java.nio.file.Path;

public class LineChartProperties extends Properties {

    LineChart owner;

    boolean showLegend = true;

    Orientation legendOrientation;

    boolean showBottomValues = true;

    boolean showLeftValues = true;

    int leftValuesLabelsStep = 10;

    int bottomValuesLabelStep = 10;

    Font fontUsed = Font.font("Arial", 12);

    @Override
    public void loadData(Path path) {

    }

    public int average(int[] values) { // use this to generate default step
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    public LineChartProperties(LineChart owner, boolean showLegend, Orientation legendOrientation, boolean showBottomValues, boolean showLeftValues, int leftValuesLabelsStep, int bottomValuesLabelStep, Font fontUsed) {
        this.owner = owner;
        this.showLegend = showLegend;
        this.legendOrientation = legendOrientation;
        this.showBottomValues = showBottomValues;
        this.showLeftValues = showLeftValues;
        this.leftValuesLabelsStep = leftValuesLabelsStep;
        this.bottomValuesLabelStep = bottomValuesLabelStep;
        this.fontUsed = fontUsed;
    }

    public LineChartProperties(boolean showLegend, boolean showBottomValues, boolean showLeftValues,Orientation legendOrientation, int leftValuesLabelsStep, int bottomValuesLabelStep, Font fontUsed) {
        this.showLegend = showLegend;
        this.legendOrientation = legendOrientation;
        this.showBottomValues = showBottomValues;
        this.showLeftValues = showLeftValues;
        this.leftValuesLabelsStep = leftValuesLabelsStep;
        this.bottomValuesLabelStep = bottomValuesLabelStep;
        this.fontUsed = fontUsed;
    }

    public LineChartProperties(boolean showLegend, boolean showBottomValues, boolean showLeftValues, int leftValuesLabelsStep, int bottomValuesLabelStep) {
        this.showLegend = showLegend;
        this.showBottomValues = showBottomValues;
        this.showLeftValues = showLeftValues;
        this.leftValuesLabelsStep = leftValuesLabelsStep;
        this.bottomValuesLabelStep = bottomValuesLabelStep;
    }

    public LineChartProperties(boolean showLegend, boolean showBottomValues, boolean showLeftValues, int leftValuesLabelsStep) {
        this.showLegend = showLegend;
        this.showBottomValues = showBottomValues;
        this.showLeftValues = showLeftValues;
        this.leftValuesLabelsStep = leftValuesLabelsStep;
    }

    public LineChartProperties(boolean showLegend, boolean showBottomValues, boolean showLeftValues) {
        this.showLegend = showLegend;
        this.showBottomValues = showBottomValues;
        this.showLeftValues = showLeftValues;
    }

    public LineChartProperties(boolean showLegend, boolean showBottomValues) {
        this.showLegend = showLegend;
        this.showBottomValues = showBottomValues;
    }

    public LineChartProperties(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public LineChartProperties() {
    }

    public LineChart getOwner() {
        return owner;
    }

    public void setOwner(LineChart owner) {
        this.owner = owner;
    }

    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public boolean isShowBottomValues() {
        return showBottomValues;
    }

    public void setShowBottomValues(boolean showBottomValues) {
        this.showBottomValues = showBottomValues;
    }

    public boolean isShowLeftValues() {
        return showLeftValues;
    }

    public void setShowLeftValues(boolean showLeftValues) {
        this.showLeftValues = showLeftValues;
    }

    public int getLeftValuesLabelsStep() {
        return leftValuesLabelsStep;
    }

    public void setLeftValuesLabelsStep(int leftValuesLabelsStep) {
        this.leftValuesLabelsStep = leftValuesLabelsStep;
    }

    public int getBottomValuesLabelStep() {
        return bottomValuesLabelStep;
    }

    public void setBottomValuesLabelStep(int bottomValuesLabelStep) {
        this.bottomValuesLabelStep = bottomValuesLabelStep;
    }

    public Font getFontUsed() {
        return fontUsed;
    }

    public void setFontUsed(Font fontUsed) {
        this.fontUsed = fontUsed;
    }
}
