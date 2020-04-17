package com.cooksys.fundamentals.characters;

public class GoblinWarrior implements Goblin {
	
	private int health;
	
	public GoblinWarrior(int health) {
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
