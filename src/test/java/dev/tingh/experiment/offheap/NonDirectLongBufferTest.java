package dev.tingh.experiment.offheap;

import org.junit.Test;

public class NonDirectLongBufferTest extends AbstractBufferTest {

    private final NonDirectLongBuffer buffer = new NonDirectLongBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }

    @Test
    public void testSetGetByte() {
        // Do not execute.
    }
}
