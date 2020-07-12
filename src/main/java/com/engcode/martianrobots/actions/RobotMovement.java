package com.engcode.martianrobots.actions;

import com.engcode.martianrobots.constants.Martianrobotsconstants;
import com.engcode.martianrobots.exception.MartianRobotsAppException;
import com.engcode.martianrobots.model.MarsGrid;
import java.util.Scanner;

import static java.util.Arrays.*;

public class RobotMovement {

    private final MarsGrid marsGridMap = new MarsGrid();

    /**
     * Initialise the maixmum cooridates and start processing the given commands file
     */

    public void startProcess() throws MartianRobotsAppException{

        Scanner fileScanner = new Scanner(this.getClass().getResourceAsStream("/inputCommands.txt"));

        int maxCoordinateX = Integer.parseInt(fileScanner.next());
        if (isValidCoordinate(maxCoordinateX))
            marsGridMap.setMaxCoordinateX(String.valueOf(maxCoordinateX));

        int maxCoordinateY = Integer.parseInt(fileScanner.next());
        if(isValidCoordinate(maxCoordinateY))
            marsGridMap.setMaxCoordinateY(String.valueOf(maxCoordinateY));

        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            moveNewRobot(fileScanner);
        }
    }
    /**
     * Get the command instructions for each robot and move it as per the instructions.
     * @param fileScanner Get each instruction from given file
     */

    private void moveNewRobot(Scanner fileScanner) {

        RobotCommandsImpl robot = new RobotCommandsImpl(marsGridMap);

        int positionX = Integer.parseInt(fileScanner.next());
        if(isValidPosition(positionX,'X'))
            robot.setPositionX(positionX);  // set positionX

        int positionY = Integer.parseInt(fileScanner.next());
        if(isValidPosition(positionY,'Y'))
            robot.setPositionY(positionY);  // set positionX

        String orientation = fileScanner.next();
        robot.setOrientation(orientation);  // set Orientation

        fileScanner.nextLine();
        String inputCommand = fileScanner.nextLine();   // Get command instruction

        // Check the maximum command length is less than 100
        if (inputCommand.length()<= Martianrobotsconstants.MAX_COMMAND_LENGTH) {
            stream(inputCommand.split("")).forEach(robot::move);
            if (fileScanner.hasNextLine()) fileScanner.nextLine();
            System.out.print(String.valueOf(robot.getPositionX())       // print the output poistions
                    + ' '
                    + robot.getPositionY()
                    + ' '
                    + robot.getOrientation());

            if (robot.isLost()) {
                System.out.print(" LOST");
            }
            System.out.println();
        }
        else
            throw new MartianRobotsAppException("Invalid command length in input data");
    }

    public boolean isValidCoordinate(int coordinate) throws MartianRobotsAppException{
        //Check the coordinate value is greater than or equal to zero and less than or equal to 50
        if (coordinate >= Martianrobotsconstants.ZERO && coordinate <= Martianrobotsconstants.MAX_COORDINATE)
            return true;
        else
            throw new MartianRobotsAppException("Invalid coordinate in input data");
    }

    public boolean isValidPosition(int position, char coordinate) throws MartianRobotsAppException{
        //Check the x coordinate value is greater than or equal to zero and less than or equal to max position of initialisation
        if (coordinate=='X' && position >= Martianrobotsconstants.ZERO && position <= Integer.parseInt(marsGridMap.getMaxCoordinateX()))
            return true;
        //Check the Y coordinate value is greater than or equal to zero and less than or equal to max position of initialisation
        else if (coordinate=='Y' && position >= Martianrobotsconstants.ZERO && position <= Integer.parseInt(marsGridMap.getMaxCoordinateY()))
            return true;
        else
            throw new MartianRobotsAppException("Invalid coordinate in input data");
    }
}