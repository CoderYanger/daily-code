package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-21
 * Time: 22:09
 */
public class Demo23 {
    public static void main(String[] args) throws InterruptedException {
        Object object=new Object();
        System.out.println("wait 之前");
        synchronized (object){
            //这里是加锁状态
            object.wait();//wait执行过程中，是解锁状态
            //这里是加锁状态
        }
        System.out.println("wait 之后");
    }
}
