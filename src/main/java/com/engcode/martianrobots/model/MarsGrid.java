package com.engcode.martianrobots.model;

import java.util.ArrayList;
import java.util.Collection;

public class MarsGrid {

    private String maxCoordinateX;
    private String maxCoordinateY;
    private Collection<GridScentPoint> gridScentPoints;

    public String getMaxCoordinateX() {
        return maxCoordinateX;
    }

    public void setMaxCoordinateX(String maxCoordinateX) {
        this.maxCoordinateX = maxCoordinateX;
    }

    public String getMaxCoordinateY() {
        return maxCoordinateY;
    }

    public void setMaxCoordinateY(String maxCoordinateY) {
        this.maxCoordinateY = maxCoordinateY;
    }

    public Collection<GridScentPoint> getGridScentPoints() {
        if (gridScentPoints == null) {
            gridScentPoints = new ArrayList<GridScentPoint>();
        }
        return gridScentPoints;
    }

}
