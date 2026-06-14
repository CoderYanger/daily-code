package thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Demo29 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue=new LinkedBlockingQueue<>();
        for(int i=0;i<100;i++){
            queue.put("aaa");
        }
        System.out.println("队列已经满了");
        queue.put("aaa");
        System.out.println("再次尝试 put 元素");
    }
}
