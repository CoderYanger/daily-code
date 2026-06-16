package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo45 {
    public static void main(String[] args) throws InterruptedException {
        // 现在把整个任务拆成 10 个部分，每个部分视为是一个 "子任务"。
        // 可以把这 10 个子任务丢到线程池中，让线程池执行。
        // 当然也可以安排 10 个独立的线程执行。

        // 构造方法中传入的 10 表示任务的个数。
        CountDownLatch latch = new CountDownLatch(10);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int id = i;
            executor.submit(() -> {
                System.out.println("子任务开始执行：" + id);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子任务结束执行：" + id);
                latch.countDown();
            });
        }
        // 这个方法阻塞等待所有的任务结束
        //此处的 a 可理解为 all
        latch.await();
        System.out.println("所有任务执行完毕");
        executor.shutdown();

        String path="c:\\windows\\system32\\notepad.exe";
    }
}
