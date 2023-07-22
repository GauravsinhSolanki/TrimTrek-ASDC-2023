package org.example.account.userrole;

public enum RoleType {

	ADMIN("Administrator"), 
	MANAGER("Manager"), 
	BARBER("Barber"),
	CUSTOMER("Customer");

	private final String name;

	RoleType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
