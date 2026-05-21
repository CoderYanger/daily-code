package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-15
 * Time: 22:34
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        while(true){
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new MyRunnable();
        Thread t=new Thread(runnable);
        t.start();
        while(true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
