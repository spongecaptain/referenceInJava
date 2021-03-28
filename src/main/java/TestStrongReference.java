public class TestStrongReference {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*9];
        byte[] bytes_2 = bytes;
        bytes = null;
        System.gc();
        //make sure gc is done
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(bytes_2);
    }
}
