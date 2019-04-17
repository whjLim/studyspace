package com.whjlim.mytomcat;


import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 响应的封装类: 响应客户端的请求
 */
public class Response {
    /*
    * 需要关闭，拿到管道的引用
    * */
    private OutputStream os = null;
    public Response(OutputStream os){
        this.os = os;
    }
    /*
    * 响应动态输出请求
    * */
    public void writeConten(String content) throws IOException{
        String html = "HTTP/1.1 200 OK\n";
        html += "Content-Type: text/html;charset=utf-8\n";
        html += "\n";
        os.write((html+content).getBytes());
        os.flush();
        os.close();

    }

    /**
     *响应静态方法
     */
    public void writeHtmlFile(String path) throws IOException{
        String htmlTxt = FileUtils.getFileContent(path);
        writeConten(htmlTxt);
    }
    public void writeFile(String path) throws IOException{
        // 字节(万能格式)读取图片
        FileInputStream fis = new FileInputStream(path);
        byte[] buff = new byte[512];
        int len = 0;
        writeHead(path);
        while ((len = fis.read(buff))!= -1){
            os.write(buff,0,len);
        }
        fis.close();
        os.flush();
        os.close();
    }
    public void writeHead(String path) throws IOException{
        String[] suffixs = {"html","css","js","jpg","jpeg","png"};

        String html = "HTTP/1.1 200 OK\n";
        for(String suffix : suffixs){
            if(path.endsWith(suffix)){
                if(suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")){
                    html += "Content-Type: image/png\n";
                }else{
                    html += "Content-Type: text/html\n";
                }
            }
        }
        html += "\n";
        os.write(html.getBytes());
    }

}
