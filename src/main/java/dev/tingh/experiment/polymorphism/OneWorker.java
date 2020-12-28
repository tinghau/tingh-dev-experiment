package dev.tingh.experiment.polymorphism;

public class OneWorker {

    private static final Worker0 worker0 = new Worker0();

    private final AbstractWorker abstractWorker;
    private final Worker worker;

    private final byte[] data;

    public OneWorker(byte[] data) {
        this.data = data;
        this.abstractWorker = worker0;
        this.worker = worker0;
    }

    public int abstractWork() {
        return abstractWorker.abstractWork(data);
    }

    public int staticWork() {
        return Worker0.staticWork(data);
    }

    public int work() {
        return worker.work(data);
    }

    public int directWork() {
        return worker0.directWork(data);
    }
}
