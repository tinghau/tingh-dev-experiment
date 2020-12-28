package dev.tingh.experiment.polymorphism.benchmark;

import dev.tingh.experiment.polymorphism.NoWorkers;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;

public class NomorphicWorkerBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + NomorphicWorkerBenchmark.class.getSimpleName() + ".*")
                .measurementTime(TimeValue.seconds(1))
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(20)
                .measurementIterations(20)
                .forks(1)
//                .jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+TraceClassLoading", "-XX:+LogCompilation", "-XX:+PrintAssembly")
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void work(NomorphicCoderBenchmarkState state) {
        NoWorkers[] l = state.worker;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @State(Scope.Benchmark)
    public static class NomorphicCoderBenchmarkState {

        private final int count = 100;
        private NoWorkers[] worker;

        @Setup
        public void setup() {
            worker = new NoWorkers[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                worker[i] = new NoWorkers(contents);
            }
        }
    }
}
