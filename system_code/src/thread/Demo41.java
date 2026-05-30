package thread;

//直接使用 Runnable 实现1+2+……+100的功能
public class Demo41 {
    private static int total=0;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                int sum=0;
                for(int i=1;i<=100;i++){
                    sum+=i;
                }
                total=sum;
            }
        };
        Thread t=new Thread(runnable);
        t.start();
        t.join();//等待线程执行完毕
        System.out.println("total="+total);
    }
}
