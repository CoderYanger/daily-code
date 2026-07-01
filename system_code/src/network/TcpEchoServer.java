package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//TCP 服务器
public class TcpEchoServer {
    //先创建一个 serverSocket 对象
    private ServerSocket serverSocket=null;
    //这里和 UDP 服务器类似，也是在构造对象的时候，绑定端口号
    public TcpEchoServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("启动服务器");

        //这种情况一般不会使用 fixedThreadPool,意味着同时处理的客户端连接数目就固定了
        ExecutorService executorService= Executors.newCachedThreadPool();
        while(true){
            //对于 TCP 来说，需要先处理客户端发来的连接
            //通过读写 clientSocket，和客户端进行通信
            //如果没有客户端发起连接，此时 accept 就会阻塞

            //主线程负责进行 accept,每次 accept 到一个客户端，就创建一个线程，由新线程负责处理客户端的请求
            Socket clientSocket=serverSocket.accept();//而主线程则阻塞在这个位置~~
            //使用多线程的方式来调整
//            Thread t=new Thread(()->{
//                processConnection(clientSocket);
//            });
//            t.start();

            //使用线程池来调整
            executorService.submit(()->{
                processConnection(clientSocket);
            });
        }
    }

    //处理一个客户端的连接
    //可能要涉及到多个客户端的请求和响应
    private void processConnection(Socket clientSocket){
        //打印客户端的地址和端口号
        System.out.printf("[%s:%d]客户端上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        //后面读取请求的时候就写到输入流对象，然后写入输出流对象
        try(InputStream inputStream=clientSocket.getInputStream();
            OutputStream outputStream=clientSocket.getOutputStream()){
            //针对 InputStream 套了一层
            Scanner sc=new Scanner(inputStream);
            //针对 OutputStream 套了一层
            PrintWriter writer=new PrintWriter(outputStream);
            //分成三个步骤
            while(true){
                //1.读取请求并解析,可以直接 read，也可以借助 Scanner 来辅助完成
                if(!sc.hasNext()){//我们发现三个子线程都是阻塞在这个位置
                    //没有下一个数据可以读了，连接断开了
                    System.out.printf("[%s:%d] 客户端下线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
                    break;//结束循环
                }
                String request=sc.next();
                //2.根据请求计算响应
                String response=process(request);
                //3.返回响应到客户端
                //按照字节的方式填写
                //outputStream.write(response.getBytes());
                //也可以使用字符流的方式套一层
                //此时 writer 就和之前说的 System.out 是一样的效果了
                writer.println(response);
                //这里也需要刷新缓冲区
                writer.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s,resp:%s\n",clientSocket.getInetAddress(),clientSocket.getPort(),
                        request,response);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private String process(String request) {
        return request;
    }
    public static void main(String[] args) throws IOException {
        TcpEchoServer server=new TcpEchoServer(9090);
        server.start();
    }
}
