
package com.engcode.martianrobots.exception;

public class MartianRobotsAppException extends  RuntimeException{
    public MartianRobotsAppException() {
        super();
    }

    public MartianRobotsAppException(String message) {
        super(message);
    }

    public MartianRobotsAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public MartianRobotsAppException(Throwable cause) {
        super(cause);
    }

    protected MartianRobotsAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}