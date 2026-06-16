package thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Demo34 {
    public static void main(String[] args) {
        //Executors:这个就是线程池的工厂类
        //通过这类里提供的静态方法，我们就能创建一些具体的线程池
        Executors.newFixedThreadPool(4);//创建一个固定数量的线程池
        //固定数量：核心线程数和最大线程数一样~~上述固定线程数就是4

        Executors.newCachedThreadPool();//不需要填写参数
        //最大线程数是一个很大的数字（线程可以无限增加）
        ExecutorService threadPool=Executors.newFixedThreadPool(4);
        threadPool.submit(()->{
            System.out.println("hello");
        });

//        ExecutorService threadPool=Executors.newFixedThreadPool(4);
//        for(int i=0;i<1000;i++){
//            int id=i;//设成局部变量就能解决打印时 i 捕获不到的情况
//            threadPool.submit(()->{
//                System.out.println("hello"+id+","+Thread.currentThread().getName()+"正在办理业务");
//            });
//        }

        ExecutorService threadPool2=Executors.newCachedThreadPool();
        for(int i=0;i<1000;i++){
            int id=i;//设成局部变量就能解决打印时 i 捕获不到的情况
            threadPool.submit(()->{
                System.out.println("hello"+id+","+Thread.currentThread().getName()+"正在办理业务");
            });
        }
        //shutdown 能够把线程池里的线程全部关闭，但是不能保证线程池里的任务能够执行完
        //threadPool.shutdown();
    }
}
