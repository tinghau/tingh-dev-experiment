package dev.tingh.experiment.offheap;

import java.nio.ByteBuffer;

public class DirectByteBuffer implements IBuffer {

    private final ByteBuffer buffer;

    public DirectByteBuffer(int size) {
        this.buffer = ByteBuffer.allocateDirect(size);
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
