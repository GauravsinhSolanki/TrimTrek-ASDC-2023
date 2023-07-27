package com.ProjectTrial1.Projectdemo1.common;

public class ConstraintViolationException extends RuntimeException{
	private String message;
    
    public ConstraintViolationException(String message) {
        super(message);
        this.message = message;
    }
    
    public ConstraintViolationException() {
    }

}
