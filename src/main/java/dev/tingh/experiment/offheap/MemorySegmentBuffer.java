package dev.tingh.experiment.offheap;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemorySegment;

import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;

public class MemorySegmentBuffer implements IBuffer {

    private static final VarHandle byteHandle = MemoryHandles.varHandle(byte.class, ByteOrder.nativeOrder());
    private static final VarHandle longHandle = MemoryHandles.varHandle(long.class, ByteOrder.nativeOrder());

    private final MemoryAddress address;

    public MemorySegmentBuffer(int size){
        MemorySegment segment = MemorySegment.allocateNative(size);
        address = segment.baseAddress();
    }

    @Override
    public void setByte(int pos, byte value) {
        byteHandle.set(address.addOffset(pos), value);
    }

    @Override
    public byte getByte(int pos) {
        return (Byte)byteHandle.get(address.addOffset(pos));
    }

    @Override
    public void setLong(int pos, long value) {
        longHandle.set(address.addOffset(pos), value);
    }

    @Override
    public long getLong(int pos) {
        return (Long)longHandle.get(address.addOffset(pos));
    }
}
