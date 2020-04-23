package com.cooksys.ftd.assignments.collections.properties;

import com.cooksys.ftd.assignments.collections.generators.Bossman;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class BossProperties {

    @Property
    public void noOwnerConstructor(String name, int salary) {
        Boss Bossman = new Boss(name, salary);
        assertEquals("Boss#getName() did not return the value passed to new Boss(name,...)", name, Bossman.getName());
        assertEquals("Boss#getSalary() did not return the value passed to new Boss(..., salary,...)", salary,
                Bossman.getSalary());
        assertNull("Boss#getParent() did not return null when constructed without an owner", Bossman.getParent());
        assertFalse("Boss#hasParent() did not return false when constructed without an owner", Bossman.hasParent());
    }

    @Property
    public void fullConstructor(String name, int salary, @Bossman Boss owner) {
        Boss Bossman = new Boss(name, salary, owner);
        assertEquals("Boss#getName() did not return the value passed to new Boss(name,...)", name, Bossman.getName());
        assertEquals("Boss#getSalary() did not return the value passed to new Boss(..., salary,...)", salary,
                Bossman.getSalary());
        assertEquals("Boss#getParent() did not return the value passed to new Boss(..., owner)", owner,
                Bossman.getParent());
        assertTrue("Boss#hasParent() did not return true when constructed without an owner", Bossman.hasParent());
    }

    @Property
    public void noOwnerValueEquality(String name, int salary) {
        Boss a = new Boss(name, salary);
        Boss b = new Boss(name, salary);
        assertEquals(
                "Boss#equals() did not return true when both instances were constructed with the same name and salary values",
                a, b);
    }

    @Property
    public void fullValueEquality(String name, int salary, @Bossman Boss owner) {
        Boss a = new Boss(name, salary, owner);
        Boss b = new Boss(name, salary, owner);
        assertEquals(
                "Boss#equals() did not return true when both instances were constructed with the same name, salary, and owner values",
                a, b);
    }
}
