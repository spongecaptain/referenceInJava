import java.util.WeakHashMap;

public class TestWeakHashMap {
    public static void main(String[] args) throws InterruptedException {
        //init
        WeakHashMap<String, String> map = new WeakHashMap<String, String>();
        String key = new String("hello");
        map.put(key,"world");
        //检查 WeakHashMap 是否有该 key
        System.out.println(map.get("hello"));
        key = null;//delete strong reference
        System.gc();
        //make sure gc is done
        Thread.sleep(2000);
        System.out.println(map.get("hello"));
    }
}
