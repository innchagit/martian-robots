package com.engcode.martianrobots;

import com.engcode.martianrobots.actions.RobotMovement;
import com.engcode.martianrobots.exception.MartianRobotsAppException;

public class MartianRobotsApp {

    public static void main(String[] args) throws MartianRobotsAppException {
        RobotMovement robotMovement = new RobotMovement();
        robotMovement.startProcess();
    }
}
