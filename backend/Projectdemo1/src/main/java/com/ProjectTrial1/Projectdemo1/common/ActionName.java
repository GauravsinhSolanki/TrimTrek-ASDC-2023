package com.ProjectTrial1.Projectdemo1.common;

public enum ActionName {
	WATERING("Watering"),
	TRIMMING("Trimming"),
	FERTILIZING("Fertilizing"),
	SPRAYING_PESTICIDE("SprayingPesticide"),
	CLEANING("Cleaning"),
	SOIL_CULTIVATION("SoilCultivation");

	private final String actionName;
	
	public String getActionName() {
		return this.actionName;
	}

	ActionName(String actionName) {
		this.actionName = actionName;
	}
	
}
