package dev.tingh.experiment.garbagecollection.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GarbageBenchmark {

    private static final int _4KB = 4000;
    private static final int _40KB = 40000;
    private static final int _400KB = 400000;
    private static final int _4MB = 4000000;
    private static final long _4GB = 4000000000L;

    public static void run(String gc) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*." + GarbageBenchmark.class.getSimpleName() + ".*")
                .mode(Mode.SampleTime)
                .jvmArgs(gc, "-Xms4g", "-Xmx4g")
                .build();
        new Runner(options).run();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage4kB(Blackhole blackhole) {
        blackhole.consume(new byte[_4KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage40kB(Blackhole blackhole) {
        blackhole.consume(new byte[_40KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage400kB(Blackhole blackhole) {
        blackhole.consume(new byte[_400KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage4MB(Blackhole blackhole) {
        blackhole.consume(new byte[_4MB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void garbage4kB_Allocate60Percent(Blackhole blackhole) {
        blackhole.consume(getBytes(_4KB, (int)(_4GB / _4KB * .6)));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void garbage40kB_Allocate60Percent(Blackhole blackhole) {
        blackhole.consume(getBytes(_40KB, (int)(_4GB / _40KB * .6)));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void garbage400kB_Allocate60Percent(Blackhole blackhole) {
        blackhole.consume(getBytes(_400KB, (int)(_4GB / _400KB * .6)));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void garbage4MB_Allocate60Percent(Blackhole blackhole) {
        blackhole.consume(getBytes(_4MB, (int)(_4GB / _4MB * .6)));
    }

    private List<byte[]> getBytes(int bytesSize, int count) {
        List<byte[]> bytes = new ArrayList<>(count);
        for (int j = 0; j < count; j++) {
            bytes.add(new byte[bytesSize]);
        }
        return bytes;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage4kB_AllocateWhile50PercentOccupied(PopulatedBenchmarkState state, Blackhole blackhole) {
        blackhole.consume(new byte[_4KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage40kB_AllocateWhile50PercentOccupied(PopulatedBenchmarkState state, Blackhole blackhole) {
        blackhole.consume(new byte[_40KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage400kB_AllocateWhile50PercentOccupied(PopulatedBenchmarkState state, Blackhole blackhole) {
        blackhole.consume(new byte[_400KB]);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void garbage4MB_AllocateWhile50PercentOccupied(PopulatedBenchmarkState state, Blackhole blackhole) {
        blackhole.consume(new byte[_4MB]);
    }

    @State(Scope.Benchmark)
    public static class PopulatedBenchmarkState {
        public final List<byte[]> bytes = getBytes();

        public List<byte[]> getBytes() {
            List<byte[]> bytes = new ArrayList<>(1000);
            for (int i=0; i<1000; i++) {
                bytes.add(new byte[2000000]);
            }
            return bytes;
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Setup(Level.Trial)
        public void setup(Blackhole blackhole) {
            blackhole.consume(new byte[332800000]);
        }
    }
}
