package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-20
 * Time: 18:39
 */
public class Demo20 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1=new Object();
        Object locker2=new Object();
        //这个过程必须是嵌套的关系
        //必须是，拿到第一把锁，再拿第二把锁（不能释放第一把锁）
        Thread t1=new Thread(()->{
            synchronized (locker1){
                //我拿起酱油
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //我尝试拿起醋
                synchronized (locker2){
                    System.out.println("t1 线程两个锁都获取到");
                }
            }
        });
        Thread t2=new Thread(()->{
            synchronized (locker1){
                //妹子拿起醋
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new  RuntimeException(e);
                }
                //妹子尝试拿起酱油
                synchronized (locker2){
                    System.out.println("t2 线程两个锁都获取到");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
