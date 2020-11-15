package dev.tingh.experiment.offheap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractBufferTest {

    @Test
    public void testSetGetByte() {
        IBuffer buffer = getBuffer();

        buffer.setByte(0, (byte) 'a');
        buffer.setByte(1, (byte) 'b');
        buffer.setByte(2, (byte) 'c');

        assertEquals('a', buffer.getByte(0));
        assertEquals('b', buffer.getByte(1));
        assertEquals('c', buffer.getByte(2));
    }

    @Test
    public void testSetGetLong() {
        IBuffer buffer = getBuffer();

        buffer.setLong(0, Long.MAX_VALUE);
        buffer.setLong(8, Long.MIN_VALUE);

        assertEquals(Long.MAX_VALUE, buffer.getLong(0));
        assertEquals(Long.MIN_VALUE, buffer.getLong(8));
    }

    protected abstract IBuffer getBuffer();
}
