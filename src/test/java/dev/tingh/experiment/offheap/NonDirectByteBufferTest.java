package dev.tingh.experiment.offheap;

public class NonDirectByteBufferTest extends AbstractBufferTest {

    private final NonDirectByteBuffer buffer = new NonDirectByteBuffer(100);

    @Override
    protected IBuffer getBuffer() {
        return buffer;
    }
}
