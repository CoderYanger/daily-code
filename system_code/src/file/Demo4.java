package file;

import java.io.File;
import java.util.Arrays;

public class Demo4 {
    public static void main(String[] args) {
        //针对文件是无法进行 list 的
        //File file=new File("./test.txt");
        //必须针对目录来进行 list
        File file=new File("c:/");
        String[] list=file.list();
        System.out.println(Arrays.toString(list));

        File[] files=file.listFiles();
        System.out.println(Arrays.toString(files));
    }
}
