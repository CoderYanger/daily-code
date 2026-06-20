package file;

import java.io.File;

//文件重命名
public class Demo6 {
    public static void main(String[] args) {
        //将test 重命名为 test2,这个文件里面内容是不变的
        //重命名，还能够起到“移动”的作用~~
        File file=new File("./test2");
        //将test2移动到src下
        //可以看到，当前test2与src同级
        //接下来开始移动~~
        //发现移动成功~~
        File newfile=new File("./src/test2");
        boolean ret=file.renameTo(newfile);
        System.out.println("ret="+ret);
    }
}
