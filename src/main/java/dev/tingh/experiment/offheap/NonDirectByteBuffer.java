package dev.tingh.experiment.offheap;

import java.nio.ByteBuffer;

public class NonDirectByteBuffer implements IBuffer {

    private final ByteBuffer buffer;

    public NonDirectByteBuffer(int size) {
        this.buffer = ByteBuffer.allocate(size);
    }

    public void setByte(int pos, byte value) {
        buffer.put(pos, value);
    }

    public byte getByte(int pos) {
        return buffer.get(pos);
    }

    public void setLong(int pos, long value) {
        buffer.putLong(pos, value);
    }

    public long getLong(int pos) {
        return buffer.getLong(pos);
    }
}