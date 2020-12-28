package dev.tingh.experiment.polymorphism;

public class BimorphicDispatchTest {

    public static void main(String[] args) {
        IAdder adder = new Adder();

        for (int i = 0; i < 10000; i++) {
            adder.add();
        }
        System.out.println(adder.get());
    }

    private final IAdder adder = new Adder();

    public void add() {
        adder.add();
    }

    interface IAdder {
        void add();

        int get();
    }

    private static class Adder implements IAdder {
        private int x;

        public void add() {
            x++;
        }

        public int get() {
            return x;
        }
    }

    private static class Adder1 implements IAdder {
        private int x;

        public void add() {
            x++;
        }

        public int get() {
            return x;
        }
    }
}
