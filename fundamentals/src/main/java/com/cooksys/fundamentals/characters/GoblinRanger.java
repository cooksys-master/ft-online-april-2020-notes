package com.cooksys.fundamentals.characters;

public class GoblinRanger implements Goblin {
	
	private int health;
	
	public GoblinRanger(int health) {
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
