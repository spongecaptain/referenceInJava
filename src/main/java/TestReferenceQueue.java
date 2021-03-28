import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicLong;

public class TestReferenceQueue {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    private static void test() throws InterruptedException {
        byte[] bytes = new byte[1024 * 10];
        ReferenceQueue<MyWeakReference> referenceQueue = new ReferenceQueue<>();
        MyWeakReference<byte[]> weakReference = new MyWeakReference<>(bytes,referenceQueue);

        bytes = null;

        System.gc();
        //make sure gc is done
        Thread.sleep(1000);
        //在 GC 后，GC 线程将 WeakReference 实例内部的 referent 字段被置为 null
        //并且 WeakReference 实例被 ReferenceHandler 线程加入到 ReferenceQueue 队列中
        MyWeakReference poll = (MyWeakReference) referenceQueue.poll();

        System.out.println(poll==weakReference);
        System.out.println(poll.get());
        poll.releaseOneReference();
        System.out.println(poll.getCount());
    }

    static class MyWeakReference<T> extends WeakReference{

        private static AtomicLong count = new AtomicLong(0L);

        public MyWeakReference(Object referent) {
            super(referent);
        }

        public MyWeakReference(Object referent, ReferenceQueue q) {
            super(referent, q);
        }

        public long getCount(){
            return count.get();
        }

        public void releaseOneReference(){
            count.incrementAndGet();
        }
    }


}
