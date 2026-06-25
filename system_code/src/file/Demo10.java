package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-06-07
 * Time: 23:11
 */
//读一个二进制文件
public class Demo10 {
    public static void main(String[] args) {
        try(InputStream inputStream=new FileInputStream("c:/vfcompat.dll")){
            while(true){
                int data=inputStream.read();
                if(data==-1){
                    //文件读完
                    break;
                }
                System.out.printf("0x%x\n",data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
