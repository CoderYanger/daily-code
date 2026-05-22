package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 20:24
 */
public class Demo12 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            for(int i=0;i<3000;i++){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("t 线程结束");
        });
        t.start();
        t.join(3000,500);//最多等待3秒+500毫秒
        System.out.println("main 线程结束");
    }
}
