package thread;
//自己基于堆（优先级队列）实现的定时器
//写法一：基于抽象类的方式定义 MyTimerTask
//这样的定义虽然可以，但写起来有点麻烦
//abstract class MyTimerTask implements Runnable{
//    @Override
//    public abstract void run();//后续再实现run的逻辑
//}

import java.util.PriorityQueue;
import java.util.concurrent.Executors;

//写法二：持有成员 task 的方式
class MyTimerTask implements Comparable<MyTimerTask>{
    private Runnable task;
    //记录任务要执行的时刻
    private long time;
    public MyTimerTask(Runnable task,long time){
        this.task=task;
        this.time=time;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        //我们的需求是让时间最小的元素在队首
        //=0，说明this和o相等
        //<0，说明this<o
        //>0，说明this>o
        //数学上比较两个数字的大小：作差
        //下面这种情况谁减谁？？
        return (int)(this.time-o.time);
        //return (int)(o.time-this.time);
        //千万不要背！！背就一定会出错！！
        //咱就做实验，一个不行换另一个
    }
    public long getTime(){
        return time;
    }
    public void run(){
        task.run();
    }
}
//自己实现一个定时器
class MyTimer{
    private PriorityQueue<MyTimerTask> queue=new PriorityQueue<>();
    //直接使用 this 作为锁对象，当然也是Ok的，这里我们就创建新的对象处理了
    private Object locker=new Object();//加锁处理，创建锁对象

    //任务 task 以当前时刻为基准，delay 毫秒后执行
    public void schedule(Runnable task,long delay){
        synchronized (locker){
            //以入队列这个时刻作为时间基准
            //创建一个任务对象
            //其中System.currentTimeMillis()是获取当前时刻时间戳的API，返回的是一个long,毫秒级别的时间戳
            MyTimerTask timerTask=new MyTimerTask(task,System.currentTimeMillis()+delay);
            //将任务对象放入任务队列
            queue.offer(timerTask);//不是阻塞队列，不用put
            locker.notify();//唤醒操作
        }
    }
    //构造方法
    public MyTimer(){
        //创建一个线程，负责执行队列中的任务
        Thread t=new Thread(()->{
            try{//将wait的异常处理放到最外面了
                while(true){
                    synchronized (locker){
                        //先判定队列是否为空
                        while(queue.isEmpty()){//换成wait之后，这里要把if改成while，防止出现Bug
                            //这里的 sleep 时间不好设定！！
                            //Thread.sleep();
                            //continue;
                            locker.wait();
                        }
                        //取出队首元素
                        MyTimerTask task=queue.peek();
                        if(System.currentTimeMillis()<task.getTime()){
                            //当前任务时间，如果比系统时间大，说明任务执行的时机未到
                            //continue;//先不执行
                            locker.wait(task.getTime()-System.currentTimeMillis());
                        }else{
                            //时间到了，执行任务
                            task.run();
                            queue.poll();
                        }
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        t.start();
    }
}
public class Demo38 {
    public static void main(String[] args) {
        MyTimer timer=new MyTimer();
        //添加任务
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        },3000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 2000");
            }
        },2000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 1000");
            }
        },1000);

        Executors.newScheduledThreadPool(4);
    }
}
