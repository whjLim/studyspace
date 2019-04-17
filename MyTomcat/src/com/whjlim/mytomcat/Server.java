package com.whjlim.mytomcat;

import java.io.File;
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
        //H:\myProject\studyspace
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = null;
        try {
            ss = new ServerSocket(9999);
            System.out.println("服务器已初始化，等待连接.....");
            while (true){
                socket = ss.accept();
                count++;
                System.out.println("第" + count + "次连接");

                //==============拿到输入，里面有相应的请求信息===================
                InputStream input = socket.getInputStream();
                Request request = new Request(input);
                //=========返回信息=============
                OutputStream os = socket.getOutputStream();
                Response response = new Response(os);
               //==============业务逻辑================
                String uri = request.getUri();
                //判断是否请求静态资源.html css img js
                if(isStatic(uri)){
                    response.writeFile("."+uri);
                }else{

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    *
    * */
    public static boolean isStatic(String uri){
        String[] suffixs = {"html","css","js","jpg","jpeg","png"};
        boolean isStatic = false;
        for(String suffix : suffixs){
            if(uri.endsWith(suffix)){
                isStatic = true;
                break;
            }
        }
        return isStatic;
    }
}
