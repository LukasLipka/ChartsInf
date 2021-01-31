package main;

import javafx.scene.paint.Color;

import java.nio.file.Path;

public class ChartFieldProperties extends Properties {
    public boolean renderBorder;

    public int borderWidth;

    public Color borderColor;

    @Override
    public void loadData(Path path) {

    }

    public ChartFieldProperties() {

    }

    public ChartFieldProperties(boolean renderBorder, int borderWidth, Color borderColor) {
        this.renderBorder = renderBorder;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }

    public ChartFieldProperties(boolean renderBorder, int borderWidth) {
        this.renderBorder = renderBorder;
        this.borderWidth = borderWidth;
    }

    public ChartFieldProperties(boolean renderBorder) {
        this.renderBorder = renderBorder;
    }
}
