package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 14:57
 */
class Counter{
    private int count=0;
    synchronized void add(){
        count++;
    }
    public int get(){
        return count;
    }
    public synchronized static void func(){
        synchronized (Counter.class){

        }
    }
}
public class Demo18 {
    public static void main(String[] args) throws InterruptedException {

        StringBuilder sb=new StringBuilder();

        Object locker=new Object();
        Counter counter=new Counter();
        Thread t1=new Thread(()->{
            for(int i=0;i<50000;i++){
                //synchronized (locker){
                    counter.add();
                //}
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<50000;i++){
                //synchronized (locker){
                    counter.add();
                //}
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count="+counter.get());
    }
}
