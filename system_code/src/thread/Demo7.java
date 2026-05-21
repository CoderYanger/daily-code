package thread;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 11:56
 */
public class Demo7 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while(true){
                System.out.println("hello Thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //这样的设置得在start之前进行
        //t.setDaemon(true);//将t设置为后台线程
        t.start();
        for(int i=0;i<3;i++){
            System.out.println("hello main");
            Thread.sleep(1000);
        }
        System.out.println("main 结束");
    }
}
