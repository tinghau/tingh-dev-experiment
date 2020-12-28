package dev.tingh.experiment.polymorphism;

public class Worker1 extends AbstractWorker implements Worker {

    @Override
    public int work(byte[] work) {
        return work.length;
    }

    @Override
    public int abstractWork(byte[] work) {
        return work.length;
    }
}
