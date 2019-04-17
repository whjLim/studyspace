package com.whjlim.mytomcat;

import java.io.*;

/**
 *
 * 文件工具类：读取静态资料
 */
public class FileUtils {
    public static String getFileContent(String path){
        System.out.println(path);
        StringBuffer sb = new StringBuffer();
        FileReader fileReader = null;
        BufferedReader br = null;
        //字符流
        try {
            fileReader  = new FileReader(path);
            br = new BufferedReader(fileReader);
            String line = null;
            while ((line=br.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }
}
