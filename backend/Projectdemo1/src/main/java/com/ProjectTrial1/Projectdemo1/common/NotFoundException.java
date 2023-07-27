package com.ProjectTrial1.Projectdemo1.common;

public class NotFoundException extends RuntimeException{
	
	    private String message;
	    
	    public NotFoundException(String message) {
	        super(message);
	        this.message = message;
	    }
	    
	    public NotFoundException() {
	    }
	
}
