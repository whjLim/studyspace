package com.whjlim.iostu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by BlackWatch on 2019/9/18 17:12
 */
public class BioHttpServer {
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
        }, 0, 3000);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println(Thread.currentThread().getName() + "启动 " + 8088);
        while (true){//java 从操作系统取出 网络连接进行 --aceept
            Socket socket = serverSocket.accept(); // 获取一个网络连接 -- 阻塞 ---如果没有新的连接建立，那么线程阻塞
            clientNums++;
            System.out.println("第" + clientNums + "次连接");
            threadPoolExecutor.submit(()->{
               while (true){
                   byte[] requestBody = new byte[1024];
                   socket.getInputStream().read(requestBody); //如果没有数据，线程阻塞
                   String request = new String(requestBody);

                   // TODO 处理请求... 调用servlet, 触发springmvc... controller
                   //System.out.println(request);
                   String responseContent = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello World";
                   socket.getOutputStream().write(responseContent.getBytes());
                   socket.getOutputStream().flush();
                   requestNums++;
                   System.out.println("第" + requestNums + "次请求处理");
               }
            });
        }
    }

}
