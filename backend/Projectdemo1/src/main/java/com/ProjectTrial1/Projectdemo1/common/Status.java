package com.ProjectTrial1.Projectdemo1.common;

public enum Status {
GREEN("Green"),
YELLOW("Yellow"),
RED("Red");

	private final String status;
	
	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
	
}
