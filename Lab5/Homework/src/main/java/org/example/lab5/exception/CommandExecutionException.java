package org.example.lab5.exception;

public class CommandExecutionException extends Exception{
    public CommandExecutionException(String message) {
        super(message);
    }
    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
