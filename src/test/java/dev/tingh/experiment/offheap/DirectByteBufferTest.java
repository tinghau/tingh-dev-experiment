package dev.tingh.experiment.offheap;

public class DirectByteBufferTest extends AbstractBufferTest {

    private final DirectByteBuffer buffer = new DirectByteBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }
}
