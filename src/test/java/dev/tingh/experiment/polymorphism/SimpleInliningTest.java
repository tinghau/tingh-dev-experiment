package dev.tingh.experiment.polymorphism;

public class SimpleInliningTest {

    public static void main(String[] args) {
        new SimpleInliningTest().run();
    }

    public long add(long a, long b) {
        return a+b;
    }

    public void run() {
        long sum = 0;
        for (int i=0; i<1000000; i++) {
            sum = add(sum,1);
        }
        System.out.println("Sum:" + sum);
    }
}
