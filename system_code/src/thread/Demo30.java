package thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Demo30 {
    public static void main(String[] args) {
        //至少生产者一个线程，消费者一个线程
        BlockingQueue<Integer> queue=new LinkedBlockingQueue<>();
        Thread producer=new Thread(()->{
            int n=0;
            while(true){
                try {
                    queue.put(n);//往队列里塞元素
                    System.out.println("生产元素"+n);
                    n++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer");
        Thread consumer=new Thread(()->{
            while(true){
                try {
                    int n=queue.take();//从队列里取元素
                    System.out.println("消费元素"+n);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"consumer");
        //启动两个线程
        producer.start();
        consumer.start();
    }
}
