package thread;

import java.util.concurrent.locks.ReentrantLock;

public class Demo42 {
    private static int count=0;
    public static void main(String[] args) {
        ReentrantLock locker=new ReentrantLock(true);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    locker.lock();//加锁
                    count++;
                    locker.unlock();//解锁
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50000;i++){
                    locker.lock();//加锁
                    try{
                        count++;
                    }finally {
                        locker.unlock();//解锁
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
