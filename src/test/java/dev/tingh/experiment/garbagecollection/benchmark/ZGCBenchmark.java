package dev.tingh.experiment.garbagecollection.benchmark;

import org.openjdk.jmh.runner.RunnerException;

public class ZGCBenchmark {

    public static void main(String[] args) throws RunnerException {
        GarbageBenchmark.run("-XX:+UseZGC");
    }
}
