package dev.tingh.experiment.offheap;

import org.junit.Test;

public class UnsafeBufferTest extends AbstractBufferTest {

    private final UnsafeBuffer buffer = new UnsafeBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }

    @Test
    public void testSetGetLong() {
        int size = Integer.MAX_VALUE / 8;
        IBuffer buffer = new UnsafeBuffer(size * 8);

        for (int i = 0; i < size; i++) {
            buffer.setLong(i*8, i);
        }

        for (int i = 0; i< Integer.MAX_VALUE; i++) {
            buffer.getLong((i%size) *8);
        }
    }

}
