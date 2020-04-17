package com.cooksys.fundamentals.characters;

public class GoblinWizard implements Goblin {
	
	private int health;
	
	public GoblinWizard(int health) {
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
