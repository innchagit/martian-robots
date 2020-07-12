package com.engcode.martianrobots.actions;

import com.engcode.martianrobots.model.MarsGrid;
import com.engcode.martianrobots.model.GridScentPoint;

public class RobotCommandsImpl implements RobotCommands {

    private int positionX = 0;
    private int positionY = 0;
    private String orientation = "N";
    private final MarsGrid marsGridMap;
    private boolean lost;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public boolean isLost() { return lost; }

    public RobotCommandsImpl(MarsGrid marsGridMap) {
        this.marsGridMap = marsGridMap;
    }

    public RobotCommandsImpl turnLeft() {
        if ("N".equals(orientation)) {
            orientation = "W";
        } else if ("E".equals(orientation)) {
            orientation = "N";
        } else if ("S".equals(orientation)) {
            orientation = "E";
        } else if ("W".equals(orientation)) {
            orientation = "S";
        }
        return this;
    }

    public RobotCommandsImpl turnRight() {
        if ("N".equals(orientation)) {
            orientation = "E";
        } else if ("E".equals(orientation)) {
            orientation = "S";
        } else if ("S".equals(orientation)) {
            orientation = "W";
        } else if ("W".equals(orientation)) {
            orientation = "N";
        }
        return this;
    }

    public RobotCommandsImpl moveForward() {
        if ("N".equals(orientation)) {
            positionY++;
        } else if ("E".equals(orientation)) {
            positionX++;
        } else if ("S".equals(orientation)) {
            positionY--;
        } else if ("W".equals(orientation)) {
            positionX--;
        }
        return this;
    }

    public RobotCommandsImpl move(String command) {
        if (!lost) {

            if ("L".equals(command))
                turnLeft();
            else if ("R".equals(command))
                turnRight();
            else if ("F".equals(command)) {

                if (positionX == Integer.valueOf(marsGridMap.getMaxCoordinateX()) && "E".equals(orientation) || positionY == Integer.valueOf(marsGridMap.getMaxCoordinateY()) && "N".equals(orientation)) {

                    boolean scentPointFound = false;
                    if (marsGridMap.getGridScentPoints().stream()
                            .filter(gridScentPoint -> gridScentPoint.getPositionX() == this.getPositionX()
                                    && gridScentPoint.getPositionY() == this.getPositionY()
                                    && gridScentPoint.getOrientation().equals(this.getOrientation())).count() > 0) {

                        scentPointFound = true;
                    }

                    if (!scentPointFound) {
                        lost = true;
                        GridScentPoint gridScentPoint = new GridScentPoint();
                        gridScentPoint.setPositionX(this.getPositionX());
                        gridScentPoint.setPositionY(this.getPositionY());
                        gridScentPoint.setOrientation(this.getOrientation());
                        marsGridMap.getGridScentPoints().add(gridScentPoint);
                    }
                } else {
                    moveForward();
                }
            }
        }
        return this;
    }
}