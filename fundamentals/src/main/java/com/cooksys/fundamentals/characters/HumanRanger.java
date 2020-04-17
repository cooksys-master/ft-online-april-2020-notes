package com.cooksys.fundamentals.characters;

public class HumanRanger implements Human {
	
	private int health;

	public HumanRanger(int health) {
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
