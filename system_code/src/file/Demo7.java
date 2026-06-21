package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//InputStream及FileInputStream的使用
public class Demo7 {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream=null;
//        try{
//            inputStream=new FileInputStream("./test.txt");
//        }finally{
//            inputStream.close();//关闭文件
//        }
        try(InputStream inputStream=new FileInputStream("./test.txt")){
            //读文件操作
            while(true){
                //一次读一个字节
//                int data=inputStream.read();
//                if(data==-1){
//                    //文件读完
//                    break;
//                }
//                System.out.printf("0x%x\n",data);//16进制方式打印

                //一次读多个字节，数组的长度，自定定义
                byte[] data=new byte[3];
                //读操作，就会尽可能把字节数组给填满
                //填不满的话，能填几个就是几个
                //此处的 n 就表示时机读了几个字节
                int n=inputStream.read(data);
                System.out.println("本次读到的字节个数n="+n);
                if(n==-1){
                    //文件读完
                    break;
                }
                for(int i=0;i<n;i++){//理想是1024，但实际长度只有n
                    System.out.printf("0x%x\n",data[i]);
                }
                System.out.println("=======================");
            }
        }//只要出了括号，就会自动调用close()方法
    }
}
