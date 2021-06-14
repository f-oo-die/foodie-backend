package com.foodie.api.exceptions;

public class SpringFoodieException extends RuntimeException {
    public SpringFoodieException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringFoodieException(String exMessage) {
        super(exMessage);
    }
}
