package dev.tingh.experiment.polymorphism;

public class Worker0 extends AbstractWorker implements Worker {
    public static int staticWork(byte[] work) {
        return work.length;
    }

    @Override
    public int work(byte[] work) {
        return work.length;
    }

    @Override
    public int abstractWork(byte[] work) {
        return work.length;
    }

    public int directWork(byte[] work) {
        return work.length;
    }
}
