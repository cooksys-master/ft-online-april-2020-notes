package com.cooksys.fundamentals.characters;

public class HumanWarrior implements Human {
	
	private int health;
	
	public HumanWarrior() {
	}
	
	public HumanWarrior(int health) {
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
