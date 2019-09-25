package com.whjlim.iostu;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by BlackWatch on 2019/9/18 15:29
 */
public class BenchMarkTests {
    public static void main(String[] args) throws IOException {
        // http 连接池  -- 访问接口  微信 支付宝接口 --- Tomcat
        // http请求结束之后，连接不断开， TCP连接复用
        //http 协议 --- keep-alive 保持存活
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        //并发发起20http请求
        CyclicBarrier cyclicBarrier = new CyclicBarrier(50);
        for(int i = 0; i < 50; i++){
            new Thread(()->{
                CloseableHttpResponse response = null;
                try {
                    cyclicBarrier.await();
                    response = httpClient.execute(new HttpGet("http://localhost:8080/"));
                    System.out.println(response.getStatusLine());
                    response.getEntity().getContent().close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        System.in.read();
        System.out.println("客户端手动关闭，当前连接池状态：" + cm.getTotalStats().toString());
    }
}
