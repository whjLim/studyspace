# Tomcat底层学习
## http请求

## 一些小坑
Server.java类中，返回的信息，如果没有加请求头(如下所示)，除了IE，其他浏览器会无法解析响应
    
	String html = "HTTP/1.1 200 OK\n";
    html += "Content-Type: text/html;charset=utf-8\n";
    html += "\n";
    html += "<html><head><title>学习使我快乐</title></head><body>当前学习时间：" + ctime + "</body></html>";
    os.write(html.getBytes());
    os.flush();
    os.close();