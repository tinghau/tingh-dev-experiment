package dev.tingh.experiment.polymorphism.benchmark;

import dev.tingh.experiment.polymorphism.SimpleInliningTest;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class SimpleInliningBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + SimpleInliningBenchmark.class.getSimpleName() + ".*")
                .warmupIterations(20)
                .measurementIterations(20)
                .forks(1)
                .mode(Mode.SingleShotTime)
                .jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+TraceClassLoading", "-XX:+LogCompilation", "-XX:+PrintAssembly")
                .build();
        new Runner(options).run();
    }

    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Benchmark
    public void add(SimpleInliningBenchmarkState state) {
        for (int i=0; i<10000; i++) {
            state.test.add(state.sum, 1);
        }
    }

    @State(Scope.Benchmark)
    public static class SimpleInliningBenchmarkState {
        private SimpleInliningTest test = new SimpleInliningTest();
        private long sum = 0;
    }
}
