package dev.tingh.experiment.offheap.benchmark;

import dev.tingh.experiment.offheap.IBuffer;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import dev.tingh.experiment.offheap.NonDirectLongBuffer;

import static dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.RANDOM_LONGS;

public class NonDirectLongBufferBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + NonDirectLongBufferBenchmark.class.getSimpleName() + ".*")
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(5)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void putLong(EmptyNonDirectBufferState state) {
        state.buffer.setLong(state.index, RANDOM_LONGS[state.index]);
        state.index++;
    }

    @Benchmark
    public void getLong(PopulatedNonDirectLongBufferState state, Blackhole blackhole) {
        blackhole.consume(state.buffer.getLong(state.getThenIncrement()));
    }

    @State(Scope.Benchmark)
    public static class EmptyNonDirectBufferState extends AbstractBufferBenchmark.EmptyBufferState {

        @Override
        protected IBuffer getBuffer(int size) {
            return new NonDirectLongBuffer(size);
        }
    }

    @State(Scope.Benchmark)
    public static class PopulatedNonDirectLongBufferState extends AbstractBufferBenchmark.PopulatedLongBufferState {
        @Override
        protected IBuffer getPopulatedBuffer(int size) {
            IBuffer buffer = new NonDirectLongBuffer(size * 8);

            for (int i = 0; i < size; i++) {
                buffer.setLong(i, i);
            }
            return buffer;
        }
    }
}
