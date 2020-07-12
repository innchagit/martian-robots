package com.engcode.martianrobots.actions;

import com.engcode.martianrobots.model.MarsGrid;
import com.engcode.martianrobots.model.GridScentPoint;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class RobotCommandsImplTest {

    private RobotCommandsImpl robot;

    @Before
    public void setup() {

        MarsGrid marsGrid = new MarsGrid();
        marsGrid.setMaxCoordinateX("5");
        marsGrid.setMaxCoordinateY("3");
        robot = new RobotCommandsImpl(marsGrid);
    }

    @Test
    public void initialState() {

        assertEquals(robot.getOrientation(), "N");
        assertEquals(robot.getPositionX(), 0);
        assertEquals(robot.getPositionY(), 0);
    }

    @Test
    public void turnLeft(){

        robot.turnLeft();
        assertEquals(robot.getOrientation(), "W");

        robot.turnLeft();
        assertEquals(robot.getOrientation(), "S");

        robot.turnLeft();
        assertEquals(robot.getOrientation(), "E");

        robot.turnLeft();
        assertEquals(robot.getOrientation(), "N");
    }

    @Test
    public void turnRight(){

        robot.turnRight();
        assertEquals(robot.getOrientation(), "E");

        robot.turnRight();
        assertEquals(robot.getOrientation(), "S");

        robot.turnRight();
        assertEquals(robot.getOrientation(), "W");

        robot.turnRight();
        assertEquals(robot.getOrientation(), "N");
    }

    @Test
    public void moveForward() {

        robot.moveForward();
        assertEquals(1, robot.getPositionY());

        robot.turnRight().moveForward();
        assertEquals(1, robot.getPositionX());

        robot.moveForward().moveForward();
        assertEquals(3, robot.getPositionX());

        robot.turnLeft().moveForward().moveForward().moveForward();
        assertEquals(4, robot.getPositionY());
    }

    @Test
    public void moveWithScentLocation() {

        MarsGrid marsGrid = new MarsGrid();
        marsGrid.setMaxCoordinateX("5");
        marsGrid.setMaxCoordinateY("3");
        GridScentPoint gridScentPoint = new GridScentPoint();
        gridScentPoint.setPositionX(3);
        gridScentPoint.setPositionY(3);
        gridScentPoint.setOrientation("N");
        marsGrid.getGridScentPoints().add(gridScentPoint);

        robot = new RobotCommandsImpl(marsGrid);

        robot.setPositionX(0);
        robot.setPositionY(3);
        robot.setOrientation("W");

        String[] commands = "LLFFFLFLFL".split("");
        Arrays.stream(commands).forEach(robot::move);

        assertEquals(2, robot.getPositionX());
        assertEquals(3, robot.getPositionY());
        assertEquals("S", robot.getOrientation());
    }

    @Test
    public void move() {

        robot.setPositionX(1);
        robot.setPositionY(1);
        robot.setOrientation("E");

        String[] commands = "RFRFRFRF".split("");
        Arrays.stream(commands).forEach(robot::move);

        assertEquals(1, robot.getPositionX());
        assertEquals(1, robot.getPositionY());
        assertEquals("E", robot.getOrientation());

        robot.setPositionX(3);
        robot.setPositionY(2);
        robot.setOrientation("N");

        commands = "FRRFLLFFRRFLL".split("");
        Arrays.stream(commands).forEach(robot::move);

        assertEquals(3, robot.getPositionX());
        assertEquals(3, robot.getPositionY());
        assertEquals("N", robot.getOrientation());
    }

}