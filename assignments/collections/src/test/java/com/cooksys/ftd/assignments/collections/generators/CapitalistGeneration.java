package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.cooksys.ftd.assignments.collections.model.Grunt;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.HashSet;
import java.util.Set;

public interface CapitalistGeneration {

    default Capitalist generateCapitalist(SourceOfRandomness random) {
        return generateCapitalist(random, 0);
    }

    default Capitalist generateCapitalist(SourceOfRandomness random, int depth) {
        return generateCapitalist(random, depth, null);
    }

    default Capitalist generateCapitalist(SourceOfRandomness random, int depth, Boss root) {
        return generateCapitalist(random, generateBoss(random, depth, root));
    }

    default Capitalist generateCapitalist(SourceOfRandomness random, Boss parent) {
        if (random.nextBoolean()) {
            return generateBoss(random, parent);
        } else {
            return generateGrunt(random, parent);
        }
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count) {
        return generateCapitalists(random, count, 0);
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count, int depth) {
        return generateCapitalists(random, count, depth, (Boss) null);
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count, int depth, Boss root) {
        Set<Boss> roots = new HashSet<>();
        roots.add(root);
        return generateCapitalists(random, count, roots);
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count, int depth, Set<Boss> roots) {
        Set<Boss> parents = new HashSet<>();
        for (Boss root : roots) {
            parents.add(generateBoss(random, depth, root));
        }
        return generateCapitalists(random, count, parents);
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count, Boss parent) {
        Set<Boss> parents = new HashSet<>();
        parents.add(parent);
        return generateCapitalists(random, count, parents);
    }

    default Set<Capitalist> generateCapitalists(SourceOfRandomness random, int count, Set<Boss> parents) {
        Set<Capitalist> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateCapitalist(random, random.choose(parents)));
        }
        return result;
    }

    default Boss generateBoss(SourceOfRandomness random) {
        return generateBoss(random, 0);
    }

    default Boss generateBoss(SourceOfRandomness random, int depth) {
        return generateBoss(random, depth, null);
    }

    default Boss generateBoss(SourceOfRandomness random, int depth, Boss root) {
        return generateBoss(random, depth > 0 ? generateBoss(random, depth - 1, root) : root);
    }

    default Boss generateBoss(SourceOfRandomness random, Boss parent) {
        String name = String.format("slave-%s", random.nextInt());
        int salary = random.nextInt((int) 1e2, (int) 1e4);
        return parent != null ? new Boss(name, salary, parent) : new Boss(name, salary);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count) {
        return generateBosss(random, count, 0);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count, int depth) {
        return generateBosss(random, count, depth, (Boss) null);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count, int depth, Boss root) {
        Set<Boss> roots = new HashSet<>();
        roots.add(root);
        return generateBosss(random, count, depth, roots);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count, int depth, Set<Boss> roots) {
        Set<Boss> parents = new HashSet<>();
        for (Boss root : roots) {
            parents.add(generateBoss(random, depth, root));
        }
        return generateBosss(random, count, parents);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count, Boss parent) {
        Set<Boss> parents = new HashSet<>();
        parents.add(parent);
        return generateBosss(random, count, parents);
    }

    default Set<Boss> generateBosss(SourceOfRandomness random, int count, Set<Boss> parents) {
        Set<Boss> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateBoss(random, random.choose(parents)));
        }
        return result;
    }

    default Grunt generateGrunt(SourceOfRandomness random) {
        return generateGrunt(random, 0);
    }

    default Grunt generateGrunt(SourceOfRandomness random, int depth) {
        return generateGrunt(random, depth, null);
    }

    default Grunt generateGrunt(SourceOfRandomness random, int depth, Boss root) {
        return generateGrunt(random, depth > 0 ? generateBoss(random, depth - 1, root) : root);
    }

    default Grunt generateGrunt(SourceOfRandomness random, Boss parent) {
        String name = String.format("slave-%s", random.nextInt());
        int salary = random.nextInt((int) 1e2, (int) 1e4);
        return parent != null ? new Grunt(name, salary, parent) : new Grunt(name, salary);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count) {
        return generateGrunts(random, count, 0);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count, int depth) {
        return generateGrunts(random, count, depth, (Boss) null);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count, int depth, Boss root) {
        Set<Boss> roots = new HashSet<>();
        roots.add(root);
        return generateGrunts(random, count, depth, roots);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count, int depth, Set<Boss> roots) {
        Set<Boss> parents = new HashSet<>();
        for (Boss root : roots) {
            parents.add(generateBoss(random, depth, root));
        }
        return generateGrunts(random, count, parents);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count, Boss parent) {
        Set<Boss> parents = new HashSet<>();
        parents.add(parent);
        return generateGrunts(random, count, parents);
    }

    default Set<Grunt> generateGrunts(SourceOfRandomness random, int count, Set<Boss> parents) {
        Set<Grunt> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateGrunt(random, random.choose(parents)));
        }
        return result;
    }
}
