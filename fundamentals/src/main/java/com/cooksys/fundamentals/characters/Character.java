package com.cooksys.fundamentals.characters;

public interface Character {
	
	public int getHealth();
	
	public void setHealth(int health);
	
	/**
	 * This method uses the character's internal health to determine if the character is alive
	 * @return - true if the characters's health is greater than 0, false otherwise
	 */
	public default boolean isAlive() {
		return getHealth() > 0;
	}
	
	/**
	 *  Applies the given amount of damage to the characters's internal health
	 * @param damage - The amount of damage this character is about to receive
	 */
	public default void takeDamage(int damage) {
		if (damage > getHealth()) {
			setHealth(0);
			return;
		}
		setHealth(getHealth() - damage);
	}
	
	public int attack();

}
