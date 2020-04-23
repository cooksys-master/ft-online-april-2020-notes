package com.cooksys.ftd.assignments.collections.hierarchy;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @param <Element> generic element type
 * @param <Parent> parent element type
 */
public interface Hierarchy<Element extends Hierarchical<Element, Parent>, Parent extends Element> {

    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param element the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    boolean add(Element element);

    /**
     * @param element the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    boolean has(Element element);

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    Set<Element> getElements();

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    Set<Parent> getParents();

    /**
     * @param parent the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    Set<Element> getChildren(Parent parent);

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    Map<Parent, Set<Element>> getHierarchy();

    /**
     * @param element
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    List<Parent> getParentChain(Element element);

}
