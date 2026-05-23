package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-18
 * Time: 18:00
 */
public class Demo15 {
    private static int count=0;
    public static void main(String[] args) throws InterruptedException {
        Object locker=new Object();
        Object locker2=new Object();
        //String s=new String();//用s当锁对象也可以
        Thread t1=new Thread(()->{
            synchronized (locker) {
                synchronized (locker) {
                    count++;
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<50000;i++){
                synchronized (locker){
                    count++;
                }
            }
            System.out.println("t2 结束");
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        //一个线程自增5w次，两个线程，总共自增10w次，预期结果，count=10_0000
        System.out.println(count);
    }
}