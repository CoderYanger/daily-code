package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//OutputStream及FileOutputStream的使用
public class Demo8 {
    public static void main(String[] args) {
        try(OutputStream outputStream=new FileOutputStream("./output.txt",true)){
            //写操作
            //97在ASCII中对应的是a
//            outputStream.write(97);
//            outputStream.write(98);
//            outputStream.write(99);

            //一次写多个字节
            byte[] data= {99};
            outputStream.write(data);
        }catch (IOException e){
            //此处是需要处理两个异常，由于此处并没有针对这两个异常提供不同的处理，就直接合并了
            throw new RuntimeException(e);
        }
    }
}
