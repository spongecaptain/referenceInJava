import java.lang.ref.WeakReference;

public class TestWeakReference {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024 * 1024 * 9];
        WeakReference<byte[]> weakReference = new WeakReference<>(bytes);
        bytes = null;//delete strong reference

        System.out.println("before gc");
        System.out.println(weakReference.get());
        //内存充足的情况下进行 GC
        System.gc();
        //make sure gc is done
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after gc");
        System.out.println(weakReference.get());
    }
}
