package com.homelearning;

/**
 * Thrown if an application tries to create {@link FixedSizeQueue} with negative {@link FixedSizeQueue#maxSize}.
 * */
public class NegativeQueueMaxSizeException extends RuntimeException {

    public NegativeQueueMaxSizeException() {
        super();
    }

    public NegativeQueueMaxSizeException(String message) {
        super(message);
    }

    public NegativeQueueMaxSizeException(int maxSize) {
        this("Max size is " + maxSize + "!");
    }
}
