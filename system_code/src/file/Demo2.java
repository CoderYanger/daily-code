package file;

import java.io.File;
import java.io.IOException;
//普通文件的创建
public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file=new File("./test.txt");

        file.createNewFile();//创建文件👈

        System.out.println(file.exists());//判断文件是否存在
        System.out.println(file.isFile());//判断是否是文件
        System.out.println(file.isDirectory());//判断是否是目录
    }
}
