package com.cooksys.serialization;

public class Shield implements Item {

	private int weight;

	public Shield() {
	}

	public Shield(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
