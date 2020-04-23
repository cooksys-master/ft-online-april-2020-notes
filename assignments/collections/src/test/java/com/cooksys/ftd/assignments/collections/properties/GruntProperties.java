package com.cooksys.ftd.assignments.collections.properties;

import com.cooksys.ftd.assignments.collections.generators.Bossman;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.cooksys.ftd.assignments.collections.model.Grunt;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class GruntProperties {

    @Property
    public void noOwnerConstructor(String name, int salary) {
        Grunt slave = new Grunt(name, salary);
        assertEquals("Grunt#getName() did not return the value passed to new Grunt(name,...)", name, slave.getName());
        assertEquals("Grunt#getSalary() did not return the value passed to new Grunt(..., salary,...)", salary,
                slave.getSalary());
        assertNull("Grunt#getParent() did not return null when constructed without an owner", slave.getParent());
        assertFalse("Grunt#hasParent() did not return false when constructed without an owner", slave.hasParent());
    }

    @Property
    public void fullConstructor(String name, int salary, @Bossman Boss owner) {
        Grunt slave = new Grunt(name, salary, owner);
        assertEquals("Grunt#getName() did not return the value passed to new Grunt(name,...)", name, slave.getName());
        assertEquals("Grunt#getSalary() did not return the value passed to new Grunt(..., salary,...)", salary,
                slave.getSalary());
        assertEquals("Grunt#getParent() did not return the value passed to new Grunt(..., owner)", owner,
                slave.getParent());
        assertTrue("Grunt#hasParent() did not return true when constructed without an owner", slave.hasParent());
    }

    @Property
    public void noOwnerValueEquality(String name, int salary) {
        Grunt a = new Grunt(name, salary);
        Grunt b = new Grunt(name, salary);
        assertEquals(
                "Grunt#equals() did not return true when both instances were constructed with the same name and salary values",
                a, b);
    }

    @Property
    public void fullValueEquality(String name, int salary, @Bossman Boss owner) {
        Grunt a = new Grunt(name, salary, owner);
        Grunt b = new Grunt(name, salary, owner);
        assertEquals(
                "Grunt#equals() did not return true when both instances were constructed with the same name, salary, and owner values",
                a, b);
    }
}
