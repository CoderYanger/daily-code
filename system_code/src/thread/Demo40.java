package thread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//使用 Callable 实现 1+2+……+100的功能
public class Demo40 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //此处 Callable 只是定义了一个“带有返回值”的任务！
        //并没有真的在执行，执行还是需要搭配 Thread 对象
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result=0;
                for(int i=1;i<=100;i++){
                    result+=i;
                }
                return result;
            }
        };
        FutureTask<Integer> futureTask=new FutureTask<>(callable);
        Thread t=new Thread(futureTask);
        t.start();
        //get 操作就是获取到 FutureTask 的返回值，这个返回值就来自于 Callable 的 call 方法
        //get 可能会阻塞，如果当前线程执行完毕，get就拿到返回结果
        //如果当前线程还没执行完毕，get 会一直阻塞
        System.out.println(futureTask.get());
    }
}
