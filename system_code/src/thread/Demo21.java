package thread;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 22:28
 */
public class Demo21 {
    private volatile static int flag=0;//加上volatile修饰
    //实现一个线程进行读取，另一个线程进行修改
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            while (flag==0){

            }
            System.out.println("t1线程结束");
        });
        Thread t2=new Thread(()->{
            //针对 flag 进行修改
            Scanner sc=new Scanner(System.in);
            System.out.println("请输入 flag 的值：");
            flag=sc.nextInt();
        });
        t1.start();
        t2.start();
    }
}
