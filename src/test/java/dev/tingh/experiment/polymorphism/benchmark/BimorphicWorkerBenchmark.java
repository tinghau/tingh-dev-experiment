package dev.tingh.experiment.polymorphism.benchmark;

import dev.tingh.experiment.polymorphism.TwoWorkers;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;

public class BimorphicWorkerBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + BimorphicWorkerBenchmark.class.getSimpleName() + ".*")
                .measurementTime(TimeValue.seconds(1))
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(20)
                .measurementIterations(20)
                .forks(1)
                .jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+TraceClassLoading", "-XX:+LogCompilation", "-XX:+PrintAssembly")
                .build();
        new Runner(options).run();
    }


    @Benchmark
    public void interfaceRef(BimorphicWorkerBenchmarkState state) {
        TwoWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].work();
        }
    }

    @Benchmark
    public void abstractRef(BimorphicWorkerBenchmarkState state) {
        TwoWorkers[] l = state.workers;
        for (int i = 0; i < state.count; i++) {
            l[i].abstractWork();
        }
    }

    @State(Scope.Benchmark)
    public static class BimorphicWorkerBenchmarkState {

        private final int count = 100;
        private TwoWorkers[] workers;

        @Setup
        public void setup() {
            workers = new TwoWorkers[count];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                byte[] contents = new byte[10];
                random.nextBytes(contents);
                workers[i] = new TwoWorkers((byte) random.nextInt(2), contents);
            }
        }
    }
}
