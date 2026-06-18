package file;

import java.io.File;

//普通文件的删除
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        File file=new File("./test.txt");
        //boolean ret=file.delete();//删除文件
        file.deleteOnExit();
        Thread.sleep(10000);
        //System.out.println(ret);
    }
}
