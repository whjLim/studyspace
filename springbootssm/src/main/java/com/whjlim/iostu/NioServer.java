package com.whjlim.iostu;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by BlackWatch on 2019/9/18 18:02
 */
public class NioServer {
    //处理请求的线程池
    public static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    //连接数
    public static int clientNums = 0;

    //请求数
    public static int requestNums = 0;

    static {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前线程活跃数：" + threadPoolExecutor.getActiveCount());
            }
        }, 0, 1000);
    }


    private static ServerSocketChannel serverSocketChannel;
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        // new IO -- 绑定端口
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("启动NIO服务器8080");


        //TODO 接受请求，处理请求  ---规避掉BIO的问题  一个线程处理一个连接，效率低

        //创建一个选择器
        selector = Selector.open(); // 疯转了操作系统特性，不同平台不同实现方式

        // 用选择器 -- 操作系统底层TCP模块 (操作系统有一个数据库，保存tcp连接)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 类似查询条件 OP_ACCEPT 8080端口有新的连接请求的时候告知

        while (true){
            selector.select(1000); //查询 -- 根据查询的条件，如果没用服务条件的，等待1s
            Set<SelectionKey> results = selector.selectedKeys(); //地城查询出来的结果(JDK --- JVM --- OS)
            Iterator<SelectionKey> iterator = results.iterator();
            while (iterator.hasNext()){
                SelectionKey result = iterator.next(); //网络事件 (新连接建立ACCEPT)

                if(result.isAcceptable()){ //新连接建立的事件
                    //拿到连接
                    SocketChannel socketChannel = serverSocketChannel.accept();  //Non Blocking I/O 非阻塞 --如果没用新链接，返回一个null
                    //TODO 拿到链接之后，是否直接丢到线程池进行下一步处理？否! 有数据在交过线程池处理
                    socketChannel.configureBlocking(false);
                    //交个选择器
                    socketChannel.register(selector, SelectionKey.OP_READ); //监听事件 -- 有数据的时候，告诉我
                    clientNums++;
                    System.out.println("第" + clientNums + "次连接");
                }else if (result.isReadable()){ //某个连接，有数据传输到服务器了
                    //再一次拿到连接，交给线程池处理
                    SocketChannel socketChannel = (SocketChannel) result.channel();
                    /*
                    *  缺少如下语句，线程数狂涨
                    * */
                    result.cancel();  //取消对该网络连接的事件处理，因为正在处理，不用在查询
                    threadPoolExecutor.submit(()->{
                       //读取 / 响应 用到NIO的新方法，不是以前inptstream outputstream
                        //读取请求内容(自己判定)
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer); // 不像inputStream，是非阻塞Non-blocking IO
                        byteBuffer.flip();
                        String request = new String(byteBuffer.array());
                        //TODO 处理请求
                        //System.out.println(request);
                        //返回一个值
                        String responseContent = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello World";
                        socketChannel.write(ByteBuffer.wrap(responseContent.getBytes()));
                        // 告诉selector 继续监听
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        return null;
                    });
                }
                iterator.remove();
            }
            //过滤掉cancel的连接
            selector.selectNow();
        }

    }
}
