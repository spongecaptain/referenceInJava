import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class TestPhantomReference {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024 * 1024 * 9];
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        PhantomReference<byte[]> weakReference = new PhantomReference<>(bytes,referenceQueue);
        //bytes = null;//无论是否进行强引用的删除，下面这一步总是返回 false
        System.out.println(weakReference.get());
    }
}
