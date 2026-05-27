package thread;
//写一个基于数组的队列
//此处就不使用泛型了，假设数据类型全是String
class MyBlockingQueue{
    private String[] data=null;
    //队首
    private int head=0;
    //队尾
    private int tail=0;
    //元素个数
    private int size=0;
    //容量
    public MyBlockingQueue(int capacity){
        data=new String[capacity];
    }
    //入队列
    public void put(String elem) throws InterruptedException {
        synchronized (this){//加锁
            while(size>= data.length){
                //队列满了，需要阻塞
                this.wait();
            }
            data[tail]=elem;
            tail++;
            if(tail>=data.length){
                tail=0;
            }
            size++;
            this.notify();//唤醒阻塞的线程
        }
    }
    //出队列
    public String take() throws InterruptedException {
        synchronized (this){//加锁
            while(size==0){
                //队列空了，需要阻塞
                this.wait();
            }
            String ret=data[head];
            head++;
            if(head>=data.length){
                head=0;
            }
            size--;
            this.notify();//唤醒阻塞的线程
            return ret;
        }
    }
}
public class Demo31 {
    public static void main(String[] args) {
        MyBlockingQueue queue=new MyBlockingQueue(1000);
        Thread producer=new Thread(()->{
            int n=0;
            while(true){
                try {
                    queue.put(n+"");
                    System.out.println("生产元素"+n);
                    n++;
                    //Thread.sleep(1000);//让消费的快点，生产的慢点
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer=new Thread(()->{
            while(true){
                String n=null;
                try {
                    n=queue.take();
                    System.out.println("消费元素"+n);
                    //Thread.sleep(1000);//让生产的快点，消费的慢点
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //启动线程
        producer.start();
        consumer.start();
    }
}
