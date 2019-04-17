package com.whjlim.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
*
* 服务类
* */
public class Server {

    //计数器
    private static int count = 0;


    public static void main(String[] args){
        ServerSocket ss = null;
        Socket socket = null;
        // 提升作用域
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = null;
        try {
            ss = new ServerSocket(9999);
            System.out.println("服务器已初始化，等待连接.....");
            while (true){
                socket = ss.accept();
                count++;
                System.out.println("第" + count + "次连接");

                //拿到输入，里面有相应的请求信息
                InputStream input = socket.getInputStream();
                byte[] buff = new byte[1024];
                //输入流每次读出来信息放入buff
                int len = input.read(buff);
                if (len > 0){
                    String msg = new String(buff,0,len);
                    System.out.println("===" + msg + "===");
                }else{
                    System.out.println("null");
                }
                //返回信息
                OutputStream os = socket.getOutputStream();
                ctime = format.format(new Date());
                String html = "HTTP/1.1 200 OK\n";
                html += "Content-Type: text/html;charset=utf-8\n";
                html += "\n";
                html += "<html><head><title>学习使我快乐</title></head><body>当前学习时间：" + ctime + "</body></html>";
                /*html = "HTTP/1.1 200 OK\n" +
                        "Date: Sat, 31 Dec 2005 23:59:59 GMT\n" +
                        "Content-Type: text/html;charset=utf-8\n" +
                        "Content-Length: 122\n" +
                        "\n" +
                        "＜html＞\n" +
                        "＜head＞\n" +
                        "＜title＞Wrox Homepage＜/title＞\n" +
                        "＜/head＞\n" +
                        "＜body＞\n" +
                        "＜!-- body goes here --＞\n" +
                        "＜/body＞\n" +
                        "＜/html＞";*/
                os.write(html.getBytes());
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
