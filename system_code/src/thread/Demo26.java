package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-22
 * Time: 16:10
 */
public class Demo26 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1=new Object();
        Object locker2=new Object();
        Object locker3=new Object();
        Thread t1=new Thread(()->{
            try{
                for(int i=0;i<10;i++){
                    synchronized (locker1){
                        locker1.wait();
                    }
                    System.out.print("A");
                    //打印完A去通知locker2，把locker2给唤醒
                    synchronized (locker2){
                        locker2.notify();
                    }
                }
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        });
        Thread t2=new Thread(()->{
            try{
                for(int i=0;i<10;i++){
                    synchronized (locker2){
                        locker2.wait();
                    }
                    System.out.print("B");
                    //打印完B去通知locker3，把locker3给唤醒
                    synchronized (locker3){
                        locker3.notify();
                    }
                }
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        });
        Thread t3=new Thread(()->{
            try{
                for(int i=0;i<10;i++){
                    synchronized (locker3){
                        locker3.wait();
                    }
                    System.out.println("C");
                    //打印完C去通知locker3，把locker3给唤醒
                    synchronized (locker1){
                        locker1.notify();
                    }
                }
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        //上述逻辑能够完成闭环循环了，但是还需要我们“推一把”，才能启动
        //需要确保上述三个线程都执行到wait，再进行notify
        Thread.sleep(1000);
        //主线程中，先通知一次locker1，让上述逻辑从t1开始执行
        synchronized (locker1){
            locker1.notify();
        }
    }
}
