package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 13:38
 */
public class Demo17 {
    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            Thread cur=Thread.currentThread();
            for(int i=0;i<50000;i++){
                synchronized (cur){
                    count++;
                }
            }
        });
        Thread t2=new Thread(()->{
            //Thread cur=Thread.currentThread();
            for(int i=0;i<50000;i++){
                synchronized (t1){
                    count++;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count="+count);
    }
}
