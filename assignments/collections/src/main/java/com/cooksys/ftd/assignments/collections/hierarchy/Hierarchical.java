package com.cooksys.ftd.assignments.collections.hierarchy;

/**
 *
 * @param <Element> generic element type
 * @param <Parent> parent element type
 */
public interface Hierarchical<Element extends Hierarchical<Element, Parent>, Parent extends Element> {

    /**
     * @return true if this element has a parent, or false otherwise
     */
    boolean hasParent();

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    Parent getParent();

}
