package com.cooksys.serialization;

public class Sword implements Item {
	
	private int weight;

	public Sword() {
	}

	public Sword(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
