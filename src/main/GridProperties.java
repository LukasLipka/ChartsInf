package main;

import javafx.scene.paint.Color;

import java.nio.file.Path;

public class GridProperties extends Properties {

    public int gridSize;

    public Color gridColor;

    public int gridWidth;

    public GridProperties(int gridSize, Color gridColor, int gridWidth) {
        this.gridSize = gridSize;
        this.gridColor = gridColor;
        this.gridWidth = gridWidth;
    }

    public GridProperties(int gridSize, Color gridColor) {
        this.gridSize = gridSize;
        this.gridColor = gridColor;
    }

    public GridProperties() {
    }

    public GridProperties(int gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    public void loadData(Path path) {

    }
}
