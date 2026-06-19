package file;

import java.io.File;
//创建目录
public class Demo5 {
    public static void main(String[] args) {
        File file=new File("./test/111/222/333");
        //mkdir 无法创建多级目录，只能创建一级
        //boolean ret=file.mkdir();
        //mkdirs 可以创建多级目录
        boolean ret=file.mkdirs();
        System.out.println("ret="+ret);
    }
}
