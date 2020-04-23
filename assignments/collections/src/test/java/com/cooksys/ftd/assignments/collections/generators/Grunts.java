package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Grunt;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class Grunts extends Generator<Grunt> implements CapitalistGeneration {
    private Cap cap;
    private GruntWorker gruntWorker;

    public Grunts() {
        super(Grunt.class);
    }

    public Grunt generate(SourceOfRandomness random, GenerationStatus status) {
        int depth = cap != null ? cap.depth() : gruntWorker != null ? gruntWorker.depth() : -1;
        return generateGrunt(random, depth >= 0 ? depth : status.size());
    }

    public void configure(Cap cap) {
        this.cap = cap;
    }

    public void configure(GruntWorker gruntWorker) {
        this.gruntWorker = gruntWorker;
    }
}
