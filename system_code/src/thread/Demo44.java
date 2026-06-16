package thread;

import java.util.concurrent.Semaphore;
//通过信号量解决线程安全问题
public class Demo44 {
    private static int count=0;
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    try{
                        semaphore.acquire();//相当于加锁操作
                        count++;
                        semaphore.release();//相当于解锁操作
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    try{
                        semaphore.acquire();//相当于加锁操作
                        count++;
                        semaphore.release();//相当于解锁操作
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
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
        System.out.println("Count="+count);
    }
}

