package thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-05-17
 * Time: 13:51
 */
class Test{
    int value=0;
}
public class Demo10 {
    public static void main(String[] args) throws InterruptedException {
        //放到这里
        boolean isFinished=false;
        Test test=new Test();
        Thread t=new Thread(()->{
            while(!isFinished){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("thread 结束");
            System.out.println(test.value);//这都是可以的
            test.value++;//这种操作也可以
        });
        t.start();
        Thread.sleep(3000);
//        isFinished=true;
    }
}
