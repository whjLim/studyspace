package com.whjlim.mytomcat;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    /*
    * 解析请求
    * 拿到-->请求url
    * */
    private String uri;
    public Request(InputStream is) throws IOException{
        byte[] buff = new byte[1024];
        //输入流每次读出来信息放入buff
        int len = is.read(buff);
        if (len > 0){
            String msg = new String(buff,0,len);
            //System.out.println(msg);

            //拿到请求资源路径
            uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1")-1);
            //System.out.println("===" + uri + "===");
        }else{
            System.out.println("null");
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
