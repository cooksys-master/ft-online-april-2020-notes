package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.MegaCorp;
import com.cooksys.ftd.assignments.collections.model.Boss;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.HashSet;
import java.util.Set;

public class MegaCorps extends Generator<MegaCorp> implements CapitalistGeneration {
    private Corp corp;

    public MegaCorps() {
        super(MegaCorp.class);
    }

    @Override
    public MegaCorp generate(SourceOfRandomness random, GenerationStatus status) {
        if (corp != null) {
            int depth = corp.depth() >= 0 ? corp.depth() : 0;
            int width = corp.width() >= 0 ? corp.width() : 0;
            int ratio = corp.ratio() >= 1 ? corp.depth() : 1;
            int size = width + (int) Math.ceil(status.size() / Math.pow(ratio, depth));
            return populate(random, depth, size, ratio);
        } else {
            return populate(random);
        }
    }

    public MegaCorp populate(SourceOfRandomness random) {
        return populate(random, 0, 0, 1);
    }

    public MegaCorp populate(SourceOfRandomness random, int depth, int size, int ratio) {
        MegaCorp corp = new MegaCorp();
        Set<Boss> parents = new HashSet<>();
        parents.add(null);
        while (depth > 0) {
            if (depth > 1) {
                parents.addAll(generateBosss(random, size, parents));
            } else {
                generateCapitalists(random, size, parents).forEach(corp::add);
            }
            size *= ratio;
            depth--;
        }
        return corp;
    }

    public void configure(Corp corp) {
        this.corp = corp;
    }
}
