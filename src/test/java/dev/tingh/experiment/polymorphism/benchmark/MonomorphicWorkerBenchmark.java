package dev.tingh.experiment.polymorphism.benchmark;

import dev.tingh.experiment.polymorphism.OneWorker;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;

public class MonomorphicWorkerBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + MonomorphicWorkerBenchmark.class.getSimpleName() + ".*")
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
    public void interfaceRef(MonomorphicWorkerBenchmarkState state) {
        OneWorker[] l = state.data;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @Benchmark
    public void abstractRef(MonomorphicWorkerBenchmarkState state) {
        OneWorker[] l = state.data;
        for (int i = 0; i < state.count; i++) {
            l[i].abstractWork();
        }
    }

    @Benchmark
    public void staticRef(MonomorphicWorkerBenchmarkState state) {
        OneWorker[] l = state.data;
        for (int i = 0; i < state.count; i++) {
            l[i].staticWork();
        }
    }

    @Benchmark
    public void directRef(MonomorphicWorkerBenchmarkState state) {
        OneWorker[] l = state.data;
        for (int i = 0; i < state.count; i++) {
            l[i].directWork();
        }
    }

    @State(Scope.Benchmark)
    public static class MonomorphicWorkerBenchmarkState {

        private final int count = 100;
        private OneWorker[] data;

        @Setup
        public void setup() {
            data = new OneWorker[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                data[i] = new OneWorker(contents);
            }
        }
    }
}
