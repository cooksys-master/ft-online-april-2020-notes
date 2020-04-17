package com.cooksys.fundamentals.characters;

public interface Human extends Character {
	
	@Override
	public default int attack() {
		return 10;
	}

}
