package file;

import java.io.File;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: CoderYanger
 * Date: 2026-06-08
 * Time: 21:11
 */
//遍历目录
public class Demo12 {
    public static void main(String[] args) {
        //准备工作
        //1.让用户指定从哪个目录开始搜
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要搜索的目录：");
        String rootDir=scanner.next();
        //2.判断该目录是否存在
        File rootFile=new File(rootDir);
        if(!rootFile.isDirectory()){
            System.out.println("输入的不是目录！");
            return;
        }
        //3.输入没问题，询问要删除的关键字
        System.out.println("请输入要删除的关键字：");
        String keyword=scanner.next();
        //开始进行目录的遍历
        //从rootFile开始搜索，按照keyword进行删除
        scanDir(rootFile,keyword);
    }

    private static void scanDir(File rootFile, String keyword) {
        //1.列出当前目录中包含的内容
        File[] files=rootFile.listFiles();
        if(files==null){
            //当前目录为空
            return;
        }
        //2.遍历当前目录中的内容
        //本质上就等同于二叉树遍历
        //只不过递归二叉树的时候是递归左子树、递归右子树
        //但是此处不是二叉，而是N叉
        //因此通过for，把每个叉（目录）都去进行遍历~~
        for(File file:files){
            //体现遍历过程
            //System.out.println("遍历目录&文件："+file.getAbsolutePath());
            //3.判断当前遍历的是目录还是普通文件
            if(file.isFile()){
                //4.如果是普通文件，则判断文件是否包含关键字
                dealFile(file,keyword);
            }else{
                //5.如果是目录，则递归调用本方法
                scanDir(file,keyword);
            }
        }
    }

    private static void dealFile(File file, String keyword) {
        if(file.getName().contains(keyword)){
            System.out.println("发现文件："+file.getAbsolutePath()+"包含关键字！是否删除?(y/n)");
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if(input.equalsIgnoreCase("y")){
                file.delete();
                System.out.println("文件已删除！");
            }
        }
    }
}
