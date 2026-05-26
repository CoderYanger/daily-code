package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 17:29
 */
class Counter2{
    private int count=0;
    synchronized void add(){
        synchronized (this){
            count++;
        }
    }
    public int get(){
        return count;
    }
}
public class Demo19 {
    public static void main(String[] args) throws InterruptedException {
        Counter2 counter=new Counter2();
        Thread t1=new Thread(()->{
            for(int i=0;i<50000;i++){
                synchronized (counter){
                    synchronized (counter){
                        synchronized (counter){
                            counter.add();
                        }
                    }
                }
            }
        });
        t1.start();
        t1.join();
        System.out.println("count="+counter.get());
    }
}
