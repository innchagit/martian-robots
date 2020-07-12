package com.engcode.martianrobots.actions;

public interface RobotCommands {
    RobotCommands turnLeft();
    RobotCommands turnRight();
    RobotCommands moveForward();
    RobotCommands move(String command);
    boolean isLost();
}