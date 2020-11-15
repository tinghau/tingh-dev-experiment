package dev.tingh.experiment.offheap;

import org.junit.Test;

public class DirectLongBufferTest extends AbstractBufferTest {

    private final DirectLongBuffer buffer = new DirectLongBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }

    @Test
    public void testSetGetByte() {
        // Do not execute.
    }
}
