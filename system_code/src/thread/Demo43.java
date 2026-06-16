package thread;
import java.util.concurrent.Semaphore;

public class Demo43 {
    //理解信号量
    public static void main(String[] args) throws InterruptedException {
        //指定可用资源的个数是“4”
        Semaphore semaphore=new Semaphore(3);
        semaphore.acquire();//可能发生阻塞，所以要抛异常
        System.out.println("进行一次 P 操作");
        semaphore.acquire();
        System.out.println("进行一次 P 操作");
        semaphore.acquire();
        System.out.println("进行一次 P 操作");
        semaphore.acquire();
        System.out.println("进行一次 P 操作");
    }
}
