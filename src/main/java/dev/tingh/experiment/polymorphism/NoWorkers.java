package dev.tingh.experiment.polymorphism;

public class NoWorkers {

    private final byte[] work;

    public NoWorkers(byte[] work) {
        this.work = work;
    }

    public int work() {
        return work.length;
    }
}
