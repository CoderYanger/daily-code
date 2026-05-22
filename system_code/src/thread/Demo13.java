package thread;
public class Demo13 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println(t.getState());
        t.start();
        Thread.sleep(1000);
        System.out.println(t.getState());
    }
}
