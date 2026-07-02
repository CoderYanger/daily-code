package network;
/**Echo 回声~~
//我们在这里通过称为回显服务器~~
//客户端给服务器发一个数据（请求）
//服务器返回一个数据（响应）
//回显服务器：请求是啥，响应就是啥~~
//真实的额服务器，请求和响应是不一定的~~
//但是当前阶段，先不考虑那些，先把API用起来，写个回显~~
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;//net：网络
import java.net.SocketException;
//UDP 服务器
public class UdpEchoServer {
    private DatagramSocket socket=null;//此处的private可省略
    //提供一个构造方法
    public UdpEchoServer(int port) throws SocketException {
        //SocketException:网络编程中常见的异常
        //指定了一个固定端口号，让服务器来使用
        socket=new DatagramSocket(port);
    }
    public void start() throws IOException {
        //启动服务器
        System.out.println("服务器启动");
        //对于服务器来说，客户端啥时候发请求，发多少个请求，我们无法预测
        //因此服务器中通常都需要有一个死循环，持续不断的尝试读取客户端的请求数据~~
        //即 7×24 小时运行~~
        while(true){
            //循环一次，就相当于处理一次请求
            //处理请求的过程，典型的服务器都是分成三个步骤的
            //1.读取请求并解析
            //  DatagramPacket 表示一个 UDP 数据报，此处传入的字节数组，就是保存 UDP 的载荷部分
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);//往上抛IOException异常
            //  把读取到的二进制数据，转成字符串，只是构造有效的部分：getData()拿到字节数组，getLength()拿到有效长度
            String request=new String(requestPacket.getData(),0,requestPacket.getLength());

            //2.根据请求，计算响应（服务器最关键的逻辑）
            //  但是此处我们写的是回显服务器，这个环节相当于省略了，请求发啥就返回啥
            String response=process(request);

            //3.把响应返回给客户端
            //  根据 response 构造 DatagramPacket，发送给客户端
            //  此处不能使用 response.length(), 因为 response.length() 返回的是String中字符的个数
            //  而 response.getBytes().length 返回的是String中字节的个数
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            //  此处还不能直接发送，因为 UDP 协议自身没有保存对方的信息（不知道发给谁）
            //  需要指定 目的IP 和 目的端口
            socket.send(responsePacket);

            //4.打印一个日志
            System.out.printf("[%s:%d] req: %s, resp: %s\n",
                    requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
        }
    }
    //后续如果要写别的服务器，只修改这个地方就好了
    //不要忘记，private 方法不能被重写，需要改成 public
    public String process(String request) {
        return request;
    }
    public static void main(String[] args) throws IOException {
        //启动服务器
        UdpEchoServer server=new UdpEchoServer(9090);
        server.start();
    }
}