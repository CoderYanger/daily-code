package file;

import java.io.File;//文件操作属于输入输出（I/O）
import java.io.IOException;
//观察 get 系列的特点和差异
public class Demo1 {
    //IOException: 文件操作/网络操作 涉及到的常见异常~~
    public static void main(String[] args) throws IOException {
        //File file=new File("D:/code/test.txt");//绝对路径
        File file=new File("./test.txt");//相对路径
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}
