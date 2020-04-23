package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Boss;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class Bosses extends Generator<Boss> implements CapitalistGeneration {
    private Cap cap;
    private Bossman bossman;

    public Bosses() {
        super(Boss.class);
    }

    public Boss generate(SourceOfRandomness random, GenerationStatus status) {
        int depth = cap != null ? cap.depth() : bossman != null ? bossman.depth() : -1;
        return generateBoss(random, depth >= 0 ? depth : status.size());
    }

    public void configure(Cap cap) {
        this.cap = cap;
    }

    public void configure(Bossman bossman) {
        this.bossman = bossman;
    }
}
