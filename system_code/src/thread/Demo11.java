package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 15:04
 */
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            //这个代码是在lambda中（也就是t线程的入口方法中）调用的
            //返回结果就是t
            //System.out.println("t:"+Thread.currentThread().getName());
            while(!Thread.currentThread().isInterrupted()){//默认返回false，如果其他线程对其进行终止操作，就会返回true
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //这个线程掀桌了……
                    //throw new RuntimeException(e);
                    //1.加上break就是立即终止
                    //2.啥都不写，就是不终止
                    //3.catch中先执行一些其他逻辑再break，就是稍后终止
                    break;
                }
            }
            System.out.println("t 结束");
        });
        t.start();

        Thread.sleep(3000);
        System.out.println("main 线程尝试终止 t 线程");
        t.interrupt();//主动去终止程序

        //这个代码是在main方法中调用的，此时返回的结果就是main
        //System.out.println("main:"+Thread.currentThread().getName());
    }
}
