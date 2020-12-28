package dev.tingh.experiment.garbagecollection.benchmark;

import org.openjdk.jmh.runner.RunnerException;

public class ShenandoahGCBenchmark {

    public static void main(String[] args) throws RunnerException {
        GarbageBenchmark.run("-XX:+UseShenandoahGC");
    }
}
