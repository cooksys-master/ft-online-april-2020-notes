package com.cooksys.serialization;

public class HealthPotion implements Item {
	
	private int weight;

	public HealthPotion() {
	}

	public HealthPotion(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
