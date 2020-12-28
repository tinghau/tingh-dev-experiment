package dev.tingh.experiment.polymorphism;

public class TwoWorkers {

    private static final Worker0 worker0 = new Worker0();
    private static final Worker1 worker1 = new Worker1();

    private final AbstractWorker abstractWorker;
    private final Worker worker;

    private final byte[] data;

    private final byte id;

    public TwoWorkers(byte id, byte[] data) {
        this.id = id;
        this.data = data;
        this.abstractWorker = id == 0 ? worker0 : worker1;
        this.worker = id == 0 ? worker0 : worker1;
    }

    public int abstractWork() {
        return abstractWorker.abstractWork(data);
    }

    public int work() {
        return worker.work(data);
    }
}
