package com.cooksys.ftd.assignments.collections.properties;

import com.cooksys.ftd.assignments.collections.MegaCorp;
import com.cooksys.ftd.assignments.collections.generators.Cap;
import com.cooksys.ftd.assignments.collections.generators.Corp;
import com.cooksys.ftd.assignments.collections.generators.GruntWorker;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.cooksys.ftd.assignments.collections.generators.Bossman;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.Grunt;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class MegaCorpProperties {

    @Property
    public void addNull(@Corp MegaCorp corp) {
        assertFalse("#add() returned true when called with null", corp.add(null));
    }

    @Property
    public void addHasParentlessGrunt(@Corp MegaCorp corp, @GruntWorker Grunt GruntWorker) {
        assertFalse("#add() returned true when called with a parent-less Grunt", corp.add(GruntWorker));
        assertFalse("#has() returned true when called with a parent-less Grunt that failed to be added",
                corp.has(GruntWorker));
    }

    @Property
    public void addHasParentlessBoss(@Corp MegaCorp corp, @Bossman Boss Bossman) {
        assertTrue("#add() returned false when called with a parent-less Boss", corp.add(Bossman));
        assertTrue("#has() returned false when called with the parent-less Boss that was just added",
                corp.has(Bossman));
    }

    @Property
    public void addHasCapitalistWithParent(@Corp MegaCorp corp, @Cap(depth = 1) Capitalist cap) {
        assertTrue("#add() returned false when called with a Capitalist", corp.add(cap));
        assertTrue("#has() returned false when called with the parent of the Capitalist that was just added",
                corp.has(cap));
        assertTrue("#has() returned false when called with the Capitalist that was just added", corp.has(cap));
    }

    @Property
    public void addHasMultipleArbitraryCapitalists(@Corp MegaCorp corp, Set<@Cap(depth = -1) Capitalist> capitalists) {
        for (Capitalist capitalist : capitalists) {
            assertTrue("#add() returned false when called with an Capitalist", corp.add(capitalist));
            assertTrue("#has() returned false when called with the Capitalist that was just added",
                    corp.has(capitalist));
            while (capitalist.hasParent()) {
                capitalist = capitalist.getParent();
                assertTrue("#has() returned false when called with a parent of the Capitalist that was just added",
                        corp.has(capitalist));
            }
        }
    }

    @Property
    public void addHasMultipleArbitraryCapitalistNoDupliBossmanes(@Corp MegaCorp corp,
            Set<@Cap(depth = -1) Capitalist> capitalists) {
        for (Capitalist capitalist : capitalists) {
            assertTrue("#add() returned false when called with an arbitrary Capitalist", corp.add(capitalist));
            assertTrue("#has() returned false when called with the arbitrary Capitalist that was just added",
                    corp.has(capitalist));
            assertFalse("#add() returned true when called with the arbitrary Capitalist that was just added",
                    corp.add(capitalist));
        }

        for (Capitalist capitalist : capitalists) {
            assertTrue("#has() returned false when called with a previously-added Capitalist", corp.has(capitalist));
            assertFalse(
                    "#add() returned true when called with a previously-added Capitalist after adding multiple Capitalists",
                    corp.add(capitalist));
        }
    }

    @Property
    public void getElementsEmpty(@Corp MegaCorp corp) {
        Set<Capitalist> elements = corp.getElements();
        assertNotNull("#getElements() returned a null value when called on an empty MegaCorp", elements);
        assertTrue("#getElements() returned a non-empty set when called on an empty MegaCorp", elements.isEmpty());
    }

    @Property
    public void getElementsDefensiveCopy(@Corp MegaCorp corp, @Cap(depth = -1) Capitalist capitalist) {
        Set<Capitalist> elements = corp.getElements();
        elements.add(capitalist);
        assertFalse("#getElements() returned a live set of elements that allowed external changes to the MegaCorp",
                corp.has(capitalist));
        elements = corp.getElements();
        assertFalse("#getElements() returned a live set of elements that allowed external changes to the MegaCorp",
                elements.contains(capitalist));
    }

    @Property
    public void getElementsMultipleArbitraryCapitalists(@Corp MegaCorp corp,
            Set<@Cap(depth = -1) Capitalist> capitalists) {
        Set<Capitalist> expected = new HashSet<>(capitalists);
        for (Capitalist capitalist : capitalists) {
            corp.add(capitalist);
            while (capitalist != null) {
                expected.add(capitalist);
                capitalist = capitalist.getParent();
            }
        }
        Set<Capitalist> elements = corp.getElements();
        assertEquals(
                "#getElements() returned a set that did not equal the set of previously-added Capitalists and their parents",
                expected, elements);
    }

    @Property
    public void getParentsEmpty(@Corp MegaCorp corp) {
        Set<Boss> parents = corp.getParents();
        assertNotNull("#getParents() returned a null value when called on an empty MegaCorp", parents);
        assertTrue("#getParents() returned a non-empty set when called on an empty MegaCorp", parents.isEmpty());
    }

    @Property
    public void getParentsDefensiveCopy(@Corp MegaCorp corp, @Bossman Boss Bossman) {
        Set<Boss> parents = corp.getParents();
        parents.add(Bossman);
        assertFalse("#getParents() returned a live set of parents that allowed external changes to the MegaCorp",
                corp.has(Bossman));
        parents = corp.getParents();
        assertFalse("#getParents() returned a live set of parents that allowed external changes to the MegaCorp",
                parents.contains(Bossman));
    }

    @Property
    public void getParentsBoss(@Corp MegaCorp corp, @Bossman Boss Bossman) {
        corp.add(Bossman);
        Set<Boss> parents = corp.getParents();
        assertFalse("#getParents() returned an empty set after adding a Boss to the MegaCorp", parents.isEmpty());
        assertEquals("#getParents() returned a set with size > 1 after adding a single Boss to the MegaCorp", 1,
                parents.size());
    }

    @Property
    public void getParentsGruntWithParent(@Corp MegaCorp corp, @GruntWorker(depth = 1) Grunt GruntWorker) {
        corp.add(GruntWorker);
        Boss parent = GruntWorker.getParent();
        Set<Boss> parents = corp.getParents();
        assertFalse("#getParents() returned an empty set after adding a Grunt with a Parent to the MegaCorp",
                parents.isEmpty());
        assertEquals(
                "#getParents() returned a set with size > 1 after adding a single Grunt with a single Parent to the MegaCorp",
                1, parents.size());
        assertTrue("#getParents() returned a set that did not contain the parent of the Grunt added to the MegaCorp",
                parents.contains(parent));
    }

    @Property
    public void getParentsMultipleArbitraryCapitalists(@Corp MegaCorp corp,
            Set<@Cap(depth = -1) Capitalist> capitalists) {
        Set<Boss> expected = new HashSet<>();
        for (Capitalist capitalist : capitalists) {
            corp.add(capitalist);
            Boss parent = capitalist instanceof Boss ? (Boss) capitalist : capitalist.getParent();
            while (parent != null) {
                expected.add(parent);
                parent = parent.getParent();
            }
        }
        Set<Boss> parents = corp.getParents();
        assertEquals("#getParents() returned a set that did not equal the set of all parents of the added Capitalists",
                expected, parents);
    }

    @Property
    public void getChildrenEmpty(@Corp MegaCorp corp, @Bossman Boss Bossman) {
        Set<Capitalist> children = corp.getChildren(Bossman);
        assertNotNull("#getChildren() returned a null value when called on an empty MegaCorp", children);
        assertTrue("#getChildren() returned a non-empty set when called on an empty MegaCorp", children.isEmpty());
    }

    @Property
    public void getChildrenDefensiveCopy(@Corp MegaCorp corp, @Bossman Boss Bossman, @Cap Capitalist capitalist) {
        corp.add(Bossman);
        Set<Capitalist> children = corp.getChildren(Bossman);
        assertTrue("#getChildren() returned a non-empty set after adding a parentless Boss to the MegaCorp",
                children.isEmpty());
        children.add(capitalist);
        assertFalse("#getChildren() returned a live set that allowed external changes to the MegaCorp",
                corp.has(capitalist));
        children = corp.getChildren(Bossman);
        assertFalse("#getChildren() returned a live set that allowed external changes to the MegaCorp",
                children.contains(capitalist));
    }

    @Property
    public void getChildrenBossWithParent(@Corp MegaCorp corp, @Bossman(depth = 1) Boss Bossman) {
        corp.add(Bossman);
        Set<Capitalist> children = corp.getChildren(Bossman);
        assertTrue(
                "#getChildren() returned a non-empty set when called with a previously-added Boss that has a single parent",
                children.isEmpty());
        children = corp.getChildren(Bossman.getParent());
        assertTrue(
                "#getChildren() returned a set that does not contain the previously-added Boss when called with its parent",
                children.contains(Bossman));
    }

    @Property
    public void getChildrenMultipleCapitalistsWithSharedParent(@Corp MegaCorp corp, @Bossman Boss parent,
            Set<@Cap Capitalist> children) {
        corp.add(parent);
        Set<Capitalist> expected = new HashSet<>();
        for (Capitalist parentless : children) {
            Capitalist withParent = parentless instanceof Boss
                    ? new Boss(parentless.getName(), parentless.getSalary(), parent)
                    : new Grunt(parentless.getName(), parentless.getSalary(), parent);
            corp.add(withParent);
            expected.add(withParent);
        }
        assertEquals(
                "@getChildren() returned a set that did not equal the set of children of the previously-added Boss",
                expected, corp.getChildren(parent));
    }

    @Property
    public void getChildrenMultipleCapitalistsSomeWithSharedParent(@Corp MegaCorp corp, @Bossman Boss parent,
            Set<@Cap Capitalist> children, Set<@Cap Capitalist> loose) {
        corp.add(parent);
        Set<Capitalist> expected = new HashSet<>();
        for (Capitalist parentless : children) {
            Capitalist withParent = parentless instanceof Boss
                    ? new Boss(parentless.getName(), parentless.getSalary(), parent)
                    : new Grunt(parentless.getName(), parentless.getSalary(), parent);
            corp.add(withParent);
            expected.add(withParent);
        }

        loose.forEach(corp::add);
        assertEquals(
                "#getChildren() returned a set that did not equal the set of children of a previously-added Boss after adding loose capitalists",
                expected, corp.getChildren(parent));
    }

    @Property
    public void getHierarchyEmpty(@Corp MegaCorp corp) {
        Map<Boss, Set<Capitalist>> hierarchy = corp.getHierarchy();
        assertNotNull("#getHierarchy() returned a null value when called on an empty MegaCorp", hierarchy);
        assertTrue("#getHierarchy() returned a non-empty Map when called on an empty MegaCorp", hierarchy.isEmpty());
    }

    @Property
    public void getHierarchyInitializesChildSets(@Corp MegaCorp corp, @Bossman Boss Bossman) {
        corp.add(Bossman);
        Set<Capitalist> children = corp.getHierarchy().get(Bossman);
        assertNotNull("#getHierarchy() returned a map with a null value for a previously-added childless Boss key",
                children);
        assertTrue("#getHierarchy() returned a map with a non-empty set for a previously-added childless Boss key",
                children.isEmpty());
    }

    @Property
    public void getHierarchyDefensiveCopy(@Corp MegaCorp corp, @GruntWorker(depth = 1) Grunt GruntWorker) {
        Set<Capitalist> children = new HashSet<>();
        children.add(GruntWorker);
        Map<Boss, Set<Capitalist>> hierarchy = corp.getHierarchy();
        hierarchy.put(GruntWorker.getParent(), children);
        assertFalse("#getHierarchy() returned a live map that allowed external changes to the MegaCorp",
                corp.has(GruntWorker.getParent()));
        assertFalse("#getHierarchy() returned a live map that allowed external changes to the MegaCorp",
                corp.has(GruntWorker));

        hierarchy = corp.getHierarchy();
        assertNotEquals("#getHierarchy() returned a live map that allowed external changes to the MegaCorp", children,
                hierarchy.get(GruntWorker.getParent()));
        assertFalse("#getHierarchy() returned a live map that allowed external changes to the MegaCorp",
                hierarchy.containsKey(GruntWorker.getParent()));

        corp.add(GruntWorker.getParent());
        hierarchy = corp.getHierarchy();
        children = hierarchy.get(GruntWorker.getParent());
        children.add(GruntWorker);
        assertFalse("#getHierarchy() returned a live map that allowed external changes to the MegaCorp",
                corp.has(GruntWorker));

        hierarchy = corp.getHierarchy();
        assertFalse("#getHierarchy() returned a live map that allowed external changes to the MegaCorp",
                hierarchy.get(GruntWorker.getParent()).contains(GruntWorker));
    }

    @Property
    public void getHierarchyConsistencyWithOneLevel(@Corp(depth = 5) MegaCorp corp) {
        Map<Boss, Set<Capitalist>> hierarchy = corp.getHierarchy();
        Set<Boss> expectedParents = corp.getParents();
        assertEquals("#getHierarchy() returned a map with a key set that did not match the MegaCorp's parents",
                expectedParents, hierarchy.keySet());
        Set<Capitalist> actualElements = new HashSet<>();
        for (Boss parent : expectedParents) {
            actualElements.add(parent);
            Set<Capitalist> expectedChildren = corp.getChildren(parent);
            actualElements.addAll(expectedChildren);
            assertEquals(
                    "#getHierarchy() returned a map in which a key's associated set of values did not match the MegaCorp's children for that key",
                    expectedChildren, hierarchy.get(parent));
        }
        assertEquals(corp.getElements(), actualElements);
    }

    @Property
    public void getParentChainEmpty(@Corp MegaCorp corp, @Cap(depth = -1) Capitalist capitalist) {
        List<Boss> actual = corp.getParentChain(null);
        assertNotNull("#getParentChain() returned a null value when called on an empty corp with null", actual);
        assertTrue("#getParentChain() returned a non-empty list when called on an empty corp with null",
                actual.isEmpty());

        actual = corp.getParentChain(capitalist);
        assertNotNull(
                "#getParentChain() returned a null value when called on an empty corp with an arbitrary Capitalist",
                actual);
        assertTrue(
                "#getParentChain() returned a non-empty list when called on an empty corp with an arbitrary Capitalist",
                actual.isEmpty());
    }

    @Property
    public void getParentChainMatchesInternalStructure(@Corp(depth = 5) MegaCorp corp,
            @Cap(depth = -1) Capitalist capitalist) {
        corp.add(capitalist);
        Boss parent = capitalist.getParent();
        List<Boss> expected = new LinkedList<>();
        while (parent != null) {
            expected.add(parent);
            parent = parent.getParent();
        }
        assertEquals(
                "#getParentChain() returned a list that did not match the calculated structure of the arbitrary Capitalist that was just added to the MegaCorp",
                expected, corp.getParentChain(capitalist));
    }
}
