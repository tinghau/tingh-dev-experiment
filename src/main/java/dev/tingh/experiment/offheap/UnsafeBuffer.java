package dev.tingh.experiment.offheap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeBuffer implements IBuffer {

    private static final Unsafe unsafe = getUnsafe();

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private final long address;

    public UnsafeBuffer(long size) {
        address = unsafe.allocateMemory(size);
    }

    @Override
    public void setByte(int pos, byte value) {
        unsafe.putByte(address + pos, value);
    }

    @Override
    public byte getByte(int pos) {
        return unsafe.getByte(address + pos);
    }

    @Override
    public void setLong(int pos, long value) {
        unsafe.putLong(address + pos, value);
    }

    @Override
    public long getLong(int pos) {
        return unsafe.getLong(address + pos);
    }
}
