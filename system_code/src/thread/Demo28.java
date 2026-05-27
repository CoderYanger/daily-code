package thread;
//通过懒汉模式构造单例模式
class Singletonlazy {
    private static volatile Singletonlazy instance=null;
    private static Object locker=new Object();
    public static Singletonlazy getInstance(){
        if(instance==null){
            synchronized (locker){
                if(instance==null){
                    instance=new Singletonlazy();
                }
            }
        }
        return instance;
    }
    public synchronized static Singletonlazy getInstance1(){
        if(instance==null){
            instance=new Singletonlazy();
        }
        return instance;
    }
    private Singletonlazy(){

    }
}
public class Demo28 {
    public static void main(String[] args) {
        Singletonlazy s1=Singletonlazy.getInstance();
        Singletonlazy s2=Singletonlazy.getInstance();
        System.out.println(s1==s2);

        //Singletonlazy s3=new Singletonlazy();
    }
}
