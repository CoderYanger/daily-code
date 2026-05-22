package thread;
public class Demo14 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while(true){
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        //t.join(6000*1000);
        t.join();
    }
}
