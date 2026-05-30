package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo39 {
    //使用原子类，代替 int
    //private static int count=0;
    private static AtomicInteger count=new AtomicInteger(0);//初始值为0
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            for(int i=0;i<50000;i++){
                //count++;
                count.getAndIncrement();
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<50000;i++){
                count.getAndIncrement();//相当于count++
                //count.IncrementAndGet();//相当于++count
                //count.addAndGet(n);//相当于count+=n
            }
        });
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Count:"+count.get());
    }
}