import java.lang.ref.SoftReference;

public class TestSoftReference {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*9];
        SoftReference<byte[]> softReference  = new SoftReference<>(bytes);
        bytes = null;//delete strong reference

        System.out.println("before gc");
        System.out.println(softReference.get());
        //内存充足的情况下进行 GC
        System.gc();
        //make sure gc is done
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after gc");
        System.out.println(softReference.get());
        //手动导致内存不足的 OOM Error
        boolean isOOM = false;
        try{
            System.out.println("after memory is not enough");
            byte[] bytes_2 = new byte[1024*1024*4];
        }catch (OutOfMemoryError e1){
            isOOM = true;
            e1.printStackTrace();
            System.out.println(softReference.get());
        }
        if(!isOOM){
            System.out.println(softReference.get());
        }
    }
}
