package com.whjlim.springioc;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BlackWatch on 2019/9/24 16:26
 */
public class ConfigUtils {
    public static Map<String, String> getClassName(String path) throws Exception{
        Map<String, String> handlerMap = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> childElementList = root.elements();
        for (Element child : childElementList){
            child.element("");
            child.element("");
        }
        return handlerMap;
    }
}
