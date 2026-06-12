package thread;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-22
 * Time: 10:46
 */
public class Demo24 {
    public static void main(String[] args) {
        Object locker=new Object();
        Object locker2=new Object();
        Thread t1=new Thread(()->{
            try{
                synchronized (locker){
                    //10s钟保证输入操作之后的notify在wait之前执行
                    Thread.sleep(10000);
                    System.out.println("wait 之前");
                    locker.wait();
                    System.out.println("wait 之后");
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        Thread t2=new Thread(()->{
            Scanner sc=new Scanner(System.in);
            System.out.println("输入任意内容，通知唤醒 t1");
            sc.next();
            synchronized (locker2){
                locker2.notify();
            }
        });
        t1.start();
        t2.start();
    }
}
