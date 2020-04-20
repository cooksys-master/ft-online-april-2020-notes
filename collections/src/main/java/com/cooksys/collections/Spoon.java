package com.cooksys.collections;

public class Spoon implements Silverware {
	
	private String type;
	
	public Spoon(String type) {
		this.type = type;
	}

	@Override
	public void carve() {
		System.out.println("Spoon carving");
	}
	
	public void scoop() {
		System.out.println("Spoon scoop");
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Spoon other = (Spoon) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
