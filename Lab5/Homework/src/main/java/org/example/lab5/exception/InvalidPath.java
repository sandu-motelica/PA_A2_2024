package org.example.lab5.exception;

import java.io.IOException;

public class InvalidPath extends IOException {
    public InvalidPath(String message) {
        super(message);
    }
}
