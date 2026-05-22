/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 12:17
 */
public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            for(int i=0;i<3;i++){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //这个结果一定是false
        //此时还没有调用start，没有真正创建线程
        System.out.println(t.isAlive());
        t.start();

        while(true){
            System.out.println(t.isAlive());//但是t对象仍然存在
            Thread.sleep(1000);
        }
    }
}
