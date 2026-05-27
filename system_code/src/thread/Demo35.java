package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//实现一个固定线程个数的线程池
class MyThreadPool {
    //任务队列
    private BlockingQueue<Runnable> queue=null;
    public MyThreadPool(int n){
        //初始化线程池，创建固定个数的线程
        //这里使用ArrayBlockingQueue作为任务队列，容量为1000
        queue=new ArrayBlockingQueue<>(1000);

        //创建N个线程（核心代码）
        for(int i=0;i<n;i++){
            Thread t=new Thread(()->{
                try{
                    while(true){
                        Runnable task=queue.take();
                        task.run();//执行任务
                    }
                }catch (InterruptedException e){
                    //两种处理方式均可，咱们就简单打印一下得了
                    e.printStackTrace();
                    //throw new RuntimeException(e);
                }
            });
            //t.setDaemon(true);//设置为后台线程
            t.start();//启动线程
        }
    }
    public void submit(Runnable task) throws InterruptedException {
        //将任务放入任务队列
        queue.put(task);
    }
}
public class Demo35 {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool=new MyThreadPool(10);
        //向线程池提交任务
        for(int i=0;i<100;i++){
            int id=i;//直接打印i编译不了，所以这里用局部变量id
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName()+"id ="+ id);
            });
        }
    }
}
