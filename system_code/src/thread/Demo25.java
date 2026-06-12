package thread;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-22
 * Time: 11:29
 */
public class Demo25 {
    public static void main(String[] args) {
        Object locker=new Object();
        Thread t1=new Thread(()->{
            try{
                System.out.println("t1 wait 之前");
                synchronized (locker){
                    locker.wait();
                }
                System.out.println("t1 wait 之后");
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        Thread t2=new Thread(()->{
            try{
                System.out.println("t2 wait 之前");
                synchronized (locker){
                    locker.wait();
                }
                System.out.println("t2 wait 之后");
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        Thread t3=new Thread(()->{
            Scanner sc=new Scanner(System.in);
            System.out.println("输入任意内容，通知唤醒所有线程");
            sc.next();
            synchronized (locker){
//                locker.notify();
                locker.notifyAll();
            }
//            System.out.println("输入任意内容，通知唤醒另一个线程");
//            sc.next();
//            synchronized (locker){
//                locker.notify();
//            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
