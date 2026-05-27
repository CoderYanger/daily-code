package thread;
//通过饿汉模式构建单例模式
class Singleton{
    private static Singleton instance=new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

    private Singleton(){

    }
}
public class Demo27 {
    public static void main(String[] args) {
        Singleton t1=Singleton.getInstance();
        Singleton t2=Singleton.getInstance();
        System.out.println(t1==t2);

        //Singleton t3=new Singleton();
    }
}
