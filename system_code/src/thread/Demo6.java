package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-16
 * Time: 17:15
 */
public class Demo6 {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            while(true){
                System.out.println("hello 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();

        Thread t2=new Thread(()->{
            while(true){
                System.out.println("hello 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t2.start();

        Thread t3=new Thread(()->{
            while(true){
                System.out.println("hello 3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t3.start();
    }
}
