package dev.tingh.experiment.offheap.benchmark;

import dev.tingh.experiment.offheap.IBuffer;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

public class AbstractBufferBenchmark {

    private static final Random RANDOM = new Random(77428665);
    protected static final byte[] RANDOM_BYTES = getRandomBytes();
    protected static final long[] RANDOM_LONGS = getRandomLongs();

    protected static final int LONG_SIZE = Integer.MAX_VALUE / 8;
    private static final int BYTE_SIZE = 700000000;

    private static byte[] getRandomBytes() {
        byte[] bytes = new byte[BYTE_SIZE];
        RANDOM.nextBytes(bytes);
        return bytes;
    }

    private static long[] getRandomLongs() {
        long[] longs = new long[LONG_SIZE];
        for (int i=0; i<LONG_SIZE; i++) {
            longs[i] = RANDOM.nextLong();
        }
        return longs;
    }

    @State(Scope.Benchmark)
    public abstract static class EmptyBufferState {
        protected IBuffer buffer = getBuffer(BYTE_SIZE);
        protected int index;

        @Setup(Level.Invocation)
        public void setup() {
            index = 0;
        }

        protected abstract IBuffer getBuffer(int size);
    }

    @State(Scope.Benchmark)
    public abstract static class PopulatedByteBufferState {
        protected IBuffer buffer = getPopulatedBuffer(BYTE_SIZE);
        protected int index;

        @Setup(Level.Iteration)
        public void setup() {
            index = 0;
        }

        protected abstract IBuffer getPopulatedBuffer(int size);
    }

    @State(Scope.Benchmark)
    public abstract static class PopulatedLongBufferState {
        protected IBuffer buffer = getPopulatedBuffer(LONG_SIZE);
        protected int index;

        @Setup(Level.Iteration)
        public void setup() {
            index = 0;
        }

        protected abstract IBuffer getPopulatedBuffer(int size);

        protected int getThenIncrement() {
            if (index > LONG_SIZE) {
                index = 0;
            }
            return index++;
        }
    }
}
