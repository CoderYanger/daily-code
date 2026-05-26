package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 23:34
 */
public class Demo22 {
    private volatile static int count=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            for(int i=0;i<50000;i++){
                count++;
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<50000;i++){
                count++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count="+count);
    }
}
