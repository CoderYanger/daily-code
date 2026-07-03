package network;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
//UDP 客户端
public class UdpEchoClient {
    private DatagramSocket socket=null;

    //UDP 本身不保存对端的信息，就在自己的代码中保存一下
    private String serverIp;
    private int serverPort;

    //和服务器不同，此处的构造方法是要指定服务器的地址（IP和端口）
    public UdpEchoClient(String serverIp,int serverPort) throws SocketException {
        this.serverIp=serverIp;
        this.serverPort=serverPort;
        //这里一定不能填写 serverPort，必须使用无参数版本~~
        //因为 serverIP 是目的IP，serverPort 是目的端口
        //源 IP：客户端所在的主机IP，源端口：随机搞一个端口（操作系统分配的空闲端口）
        socket=new DatagramSocket();
    }

    //通过 start 启动客户端程序
    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while(true){
            //1.从控制台读取用户输入的内容
            System.out.println("请输入要发送的内容：");
            //输入空行就结束（本质是读前校验）
            if(!scanner.hasNext()){
                break;
            }
            String request=scanner.next();
            //2.把请求发送给服务器，需要构造 DatagramPacket 对象
            //  构造过程中，不光要构造载荷，还要设置服务器的 IP 和 端口号
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            //3.发送数据报
            socket.send(requestPacket);
            //4.接收服务器的响应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);//4096这个长度可以自己指定
            socket.receive(responsePacket);
            //5.从服务器读取的数据进行解析，打印出来
            String response=new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println(response);
        }
    }
    public static void main(String[] args) throws IOException {
        //创建客户端并指定服务器的 IP 和 端口
        UdpEchoClient client=new UdpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
