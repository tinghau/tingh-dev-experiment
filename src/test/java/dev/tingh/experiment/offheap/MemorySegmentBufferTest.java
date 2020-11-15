package dev.tingh.experiment.offheap;

public class MemorySegmentBufferTest extends AbstractBufferTest {

    private final MemorySegmentBuffer buffer = new MemorySegmentBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }
}
