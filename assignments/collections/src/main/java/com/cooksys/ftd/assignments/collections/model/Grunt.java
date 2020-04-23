package com.cooksys.ftd.assignments.collections.model;

public class Grunt implements Capitalist {

	private String name;
	private int salary;
	private Boss owner;

	public Grunt(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public Grunt(String name, int salary, Boss owner) {
		this.name = name;
		this.salary = salary;
		this.owner = owner;
	}

	/**
	 * @return the name of the capitalist
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return the salary of the capitalist, in dollars
	 */
	@Override
	public int getSalary() {
		return salary;
	}

	/**
	 * @return true if this element has a parent, or false otherwise
	 */
	@Override
	public boolean hasParent() {
		return owner != null;
	}

	/**
	 * @return the parent of this element, or null if this represents the top of a
	 *         hierarchy
	 */
	@Override
	public Boss getParent() {
		return owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grunt other = (Grunt) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

}
