package dev.tingh.experiment.polymorphism.benchmark;

import dev.tingh.experiment.polymorphism.ThreeWorkers;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;

public class MegamorphicWorkerBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + MegamorphicWorkerBenchmark.class.getSimpleName() + ".*")
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
    public void interfaceRefThreeWorkers(MegamorphicThreeWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @Benchmark
    public void abstractRefThreeWorkers(MegamorphicThreeWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].abstractWork();
        }
    }

    @Benchmark
    public void interfaceRefTwoWorkers(MegamorphicTwoWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @Benchmark
    public void abstractRefTwoWorkers(MegamorphicTwoWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].abstractWork();
        }
    }

    @Benchmark
    public void interfaceRefOneWorker(MegamorphicOneWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @Benchmark
    public void abstractRefOneWorker(MegamorphicOneWorkerBenchmarkState state) {
        ThreeWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].abstractWork();
        }
    }

    @State(Scope.Benchmark)
    public static class MegamorphicThreeWorkerBenchmarkState {

        private final int count = 100;
        private ThreeWorkers[] workers;

        @Setup
        public void setup() {
            workers = new ThreeWorkers[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                workers[i] = new ThreeWorkers((byte) random.nextInt(3), contents);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class MegamorphicTwoWorkerBenchmarkState {

        private final int count = 100;
        private ThreeWorkers[] workers;

        @Setup
        public void setup() {
            workers = new ThreeWorkers[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                workers[i] = new ThreeWorkers((byte) random.nextInt(2), contents);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class MegamorphicOneWorkerBenchmarkState {

        private final int count = 100;
        private ThreeWorkers[] workers;

        @Setup
        public void setup() {
            workers = new ThreeWorkers[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                workers[i] = new ThreeWorkers((byte) random.nextInt(1), contents);
            }
        }
    }
}
