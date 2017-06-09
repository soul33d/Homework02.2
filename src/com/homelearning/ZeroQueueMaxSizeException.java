package com.homelearning;

/**
 * Thrown if an application tries to create {@link FixedSizeQueue} with {@link FixedSizeQueue#maxSize} equals 0.
 * */
public class ZeroQueueMaxSizeException extends RuntimeException {
    public ZeroQueueMaxSizeException() {
        super();
    }

    public ZeroQueueMaxSizeException(String message) {
        super(message);
    }


    public ZeroQueueMaxSizeException(int maxSize) {
        this("Max size is " + maxSize + "!");
    }
}
