package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 13:18
 */
public class Demo9 {
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            System.out.println("hello thread");
        });
        //t.start();
        t.start();
        //t.run();
    }
}
