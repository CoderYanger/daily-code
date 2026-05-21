package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-15
 * Time: 11:42
 */
class MyThread extends Thread{
    //run相当于线程的入口
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
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new MyThread();
        //真正再系统中创建出一个线程
        t.start();
//        t.run();
        while(true){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
