package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-18
 * Time: 18:29
 */
public class Demo16 {
    public static void main(String[] args) throws InterruptedException {
        //先拿到main线程的引用
        Thread mainThread=Thread.currentThread();

        Thread t1=new Thread(()->{
            //这样就相当于让t1线程等待main线程的结束
            try {
                System.out.println("开始等待 main");
                mainThread.join();
                System.out.println("结束等待 main");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        Thread.sleep(3000);
        System.out.println("main 结束");
    }
}
