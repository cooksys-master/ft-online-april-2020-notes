package com.cooksys.collections.generics;

public class Box<T> {
	
	private T x;
	
	public Box(T x) {
		this.x = x;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

}
