package dev.tingh.experiment.offheap;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class NonDirectLongBuffer implements IBuffer {

    private final LongBuffer buffer;

    public NonDirectLongBuffer(int size) {
        this.buffer = ByteBuffer.allocate(size).asLongBuffer();
    }

    @Override
    public void setByte(int pos, byte value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByte(int pos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLong(int pos, long value) {
        buffer.put(pos, value);
    }

    @Override
    public long getLong(int pos) {
        return buffer.get(pos);
    }
}
