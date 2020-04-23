package com.cooksys.ftd.assignments.collections.model;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchical;

public interface Capitalist extends Hierarchical<Capitalist, Boss> {

    /**
     * @return the name of the capitalist
     */
    String getName();

    /**
     * @return the salary of the capitalist, in dollars
     */
    int getSalary();
}
