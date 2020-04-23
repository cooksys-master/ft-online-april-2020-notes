package com.cooksys.ftd.assignments.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.Grunt;

public class MegaCorp implements Hierarchy<Capitalist, Boss> {

	private Map<Boss, Set<Capitalist>> hierarchy = new HashMap<>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it and
	 * return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add it
	 * and return false
	 *
	 * @param capitalist the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {
		return capitalist instanceof Boss && add((Boss) capitalist)
				|| capitalist instanceof Grunt && add((Grunt) capitalist);
	}

	private boolean add(Boss boss) {
		if (boss == null || has(boss)) {
			return false;
		}

		if (has(boss.getParent()) || add(boss.getParent())) {
			hierarchy.get(boss.getParent()).add(boss);
		}

		hierarchy.put(boss, new HashSet<>());
		return true;
	}

	private boolean add(Grunt grunt) {
		return !has(grunt) && (has(grunt.getParent()) || add(grunt.getParent()))
				&& hierarchy.get(grunt.getParent()).add(grunt);
	}

	/**
	 * @param capitalist the element to search for
	 * @return true if the element has been added to the hierarchy, false otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		return capitalist instanceof Boss && has((Boss) capitalist)
				|| capitalist instanceof Grunt && has((Grunt) capitalist);
	}

	private boolean has(Boss boss) {
		return boss != null && hierarchy.containsKey(boss);
	}

	private boolean has(Grunt grunt) {
		return grunt != null && grunt.hasParent() && has(grunt.getParent())
				&& hierarchy.get(grunt.getParent()).contains(grunt);
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements have
	 *         been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		Set<Capitalist> result = new HashSet<>();
		for (Boss boss : hierarchy.keySet()) {
			result.add(boss);
			result.addAll(hierarchy.get(boss));
		}
		return result;
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no parents
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Boss> getParents() {
		return new HashSet<>(hierarchy.keySet());
	}

	/**
	 * @param boss the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a direct
	 *         parent, or an empty set if the parent is not present in the hierarchy
	 *         or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(Boss boss) {
		return has(boss) ? new HashSet<>(hierarchy.get(boss)) : new HashSet<>();
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of the
	 *         associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<Boss, Set<Capitalist>> getHierarchy() {
		Map<Boss, Set<Capitalist>> result = new HashMap<>();
		for (Boss boss : hierarchy.keySet()) {
			result.put(boss, new HashSet<>(hierarchy.get(boss)));
		}
		return result;
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the given
	 *         element has no parent or if its parent is not in the hierarchy
	 */
	@Override
	public List<Boss> getParentChain(Capitalist capitalist) {
		List<Boss> result = new ArrayList<>();
		Boss parent = capitalist == null ? null : capitalist.getParent();
		while (has(parent)) {
			result.add(parent);
			parent = parent.getParent();
		}
		return result;
	}

}
