package com.cooksys.fundamentals.characters;

public class HumanWizard implements Human {
	
	private int health;

	public HumanWizard(int health) {
		this.health = health;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

}
