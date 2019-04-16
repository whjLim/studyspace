package com.whjlim.mytomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        try {
            ss = new ServerSocket(9999);
            System.out.println("服务器已初始化，等待连接.....");
            while (true){
                socket = ss.accept();
                count++;
                System.out.println("第" + count + "次连接");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
