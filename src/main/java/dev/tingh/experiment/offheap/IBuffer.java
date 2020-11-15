package dev.tingh.experiment.offheap;

public interface IBuffer {

    void setByte(int pos, byte value);

    byte getByte(int pos);

    void setLong(int pos, long value);

    long getLong(int pos);
}
