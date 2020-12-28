package dev.tingh.experiment.polymorphism;

public class ThreeWorkers {

    private static final Worker0 coder0 = new Worker0();
    private static final Worker1 coder1 = new Worker1();
    private static final Worker2 coder2  = new Worker2();

    private final AbstractWorker abstractWorker;
    private final Worker worker;

    private final byte[] data;

    private final byte id;

    public ThreeWorkers(byte id, byte[] data) {
        this.id = id;
        this.data = data;
        this.abstractWorker = getAbstractWorker(id);
        this.worker = getWorker(id);
    }

    private Worker getWorker(byte id) {
        switch (id) {
            case 0: return coder0;
            case 1: return coder1;
            case 2: return coder2;
            default: throw new IllegalStateException();
        }
    }

    private AbstractWorker getAbstractWorker(int id) {
        switch (id) {
            case 0: return coder0;
            case 1: return coder1;
            case 2: return coder2;
            default: throw new IllegalStateException();
        }
    }

    public int abstractWork() {
        return abstractWorker.abstractWork(data);
    }

    public int work() {
        return worker.work(data);
    }
}
