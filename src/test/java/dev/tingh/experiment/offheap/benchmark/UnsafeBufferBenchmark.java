package dev.tingh.experiment.offheap.benchmark;

import dev.tingh.experiment.offheap.IBuffer;
import dev.tingh.experiment.offheap.UnsafeBuffer;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.EmptyBufferState;
import dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.PopulatedByteBufferState;
import dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.PopulatedLongBufferState;

import static dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.RANDOM_BYTES;
import static dev.tingh.experiment.offheap.benchmark.AbstractBufferBenchmark.RANDOM_LONGS;

public class UnsafeBufferBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + UnsafeBufferBenchmark.class.getSimpleName() + ".*")
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(5)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void putByte(EmptyUnsafeBufferState state) {
        state.buffer.setByte(state.index, RANDOM_BYTES[state.index]);
        state.index++;
    }

    @Benchmark
    public void getByte(PopulatedUnsafeByteBufferState state, Blackhole blackhole) {
        blackhole.consume(state.buffer.getByte(state.index));
        state.index++;
    }

    @Benchmark
    public void putLong(EmptyUnsafeBufferState state) {
        state.buffer.setLong(state.index * 8, RANDOM_LONGS[state.index]);
        state.index++;
    }

    @Benchmark
    public void getLong(PopulatedUnsafeLongBufferState state, Blackhole blackhole) {
        blackhole.consume(state.buffer.getLong(state.getThenIncrement() * 8));
    }

    @State(Scope.Benchmark)
    public static class EmptyUnsafeBufferState extends EmptyBufferState {

        @Override
        protected IBuffer getBuffer(int size) {
            return new UnsafeBuffer(size);
        }
    }

    @State(Scope.Benchmark)
    public static class PopulatedUnsafeByteBufferState extends PopulatedByteBufferState {

        @Override
        protected IBuffer getPopulatedBuffer(int size) {
            IBuffer buffer = new UnsafeBuffer(size);

            for (int i = 0; i < size; i++) {
                buffer.setByte(i, (byte) i);
            }
            return buffer;
        }
    }

    @State(Scope.Benchmark)
    public static class PopulatedUnsafeLongBufferState extends PopulatedLongBufferState {

        @Override
        protected IBuffer getPopulatedBuffer(int size) {
            IBuffer buffer = new UnsafeBuffer(size * 8);

            for (int i = 0; i < size; i++) {
                buffer.setLong(i*8, i);
            }

            return buffer;
        }
    }
}
