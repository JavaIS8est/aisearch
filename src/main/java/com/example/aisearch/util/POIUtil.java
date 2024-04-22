package com.example.aisearch.util;

/**
 * <p>Description:POIUtil 工具类</p>
 * <p>Copyright: Copyright (c)2019</p>
 * <p>Company: Tope</p>
 * <P>@version 1.0</P>
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class POIUtil {

    /**
     * @Description: POI 读取  word
     * @create: 2019-07-27 9:48
     * @update logs
     * @throws Exception
     */
    public static JSONArray readWord(String filePath) throws Exception{
        JSONArray jas =new JSONArray();

        String linList ="" ;
        String buffer = "";
        try {
            if (filePath.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(filePath));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
                if(buffer.length() > 0){
                    //使用回车换行符分割字符串
                    String [] arry = buffer.split("\\r\\n");
                    for (String string : arry) {
                        linList=linList+string.trim();
                    }
                }
            } else if (filePath.endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
                if(buffer.length() > 0){
                    //使用换行符分割字符串
                    String [] arry = buffer.split("\\r\\n");
                    for (String string : arry) {
                        linList=linList+string.trim();
                    }
                    System.out.println(linList.length());
                }
                String [] arry = linList.split("\\n");
                List<String> ans =new ArrayList();
                List<String> que =new ArrayList();
                String answer ="";
                for (int i=0;i<arry.length;i++){
                    if(arry[i].trim().indexOf("问题：")!= -1){
                       String[] ary= arry[i].split("问题：");
//                        System.out.println(ary[1]);
                        que.add(ary[1].trim());
                    }else {
                        answer=answer+arry[i].trim();
                    }
                }
                String [] ay =answer.split("解答：");
                for (int i=1;i<ay.length;i++){
                    ans.add(ay[i].trim());
                }
                System.out.println(ans.size()+"|||"+que.size());
                for (int i=0;i<ans.size();i++){
                    JSONObject jo =new JSONObject();
                    jo.put("secondTitle",que.get(i));
                    jo.put("content",ans.get(i));
                    jas.add(jo);
                }
            } else {
                return null;
            }
            return jas;
        } catch (Exception e) {
            System.out.print("error---->"+filePath);
            e.printStackTrace();
            return null;
        }
    }
}