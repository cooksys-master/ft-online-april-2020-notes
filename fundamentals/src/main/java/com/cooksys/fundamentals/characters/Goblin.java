package com.cooksys.fundamentals.characters;

public interface Goblin extends Character {

	@Override
	public default int attack() {
		return 5;
	}

}
