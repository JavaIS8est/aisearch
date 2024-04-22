package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.aisearch.entity.JiDianShouCe;
import com.example.aisearch.entity.R;
import com.example.aisearch.entity.YingZhiYingHui;
import com.example.aisearch.service.IjidianshouceService;
import com.example.aisearch.service.IyingzhiyinghuiService;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.aisearch.util.POIUtil.readWord;

@RestController
@RequestMapping("/aisearch")
public class TestController {
    @Autowired
    private IjidianshouceService jdscService;
    @Autowired
    private IyingzhiyinghuiService yzyhService;
    /**
     * 测试
     */
    @GetMapping("/test")
    public R test() throws ClassNotFoundException, SQLException,IOException {
            try {


//                Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
//                String url = "jdbc:mysql://localhost:3306/aisearch?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=UTF-8&allowMultiQueries=true";
//                String username = "root";
//                String password = "root";
//                Connection conn = DriverManager.getConnection(url, username, password);//用参数得到连接对象
//             File file =new File("D:\\SynologyDrive\\01常用软件\\code\\database_export-sv0URQIrF_k1.json");
//             readerMethod(file);
//                 JSONArray array=readWord("D:\\0_code\\机电业务知识应知应会（修订版0719） (1).docx");
//                System.out.println(array);
//                for (int i=0;i<array.size();i++){
//                    JSONObject jsonObject =(JSONObject) array.get(i);
//                    System.out.println(jsonObject);
//                    YingZhiYingHui yzyh =new YingZhiYingHui();
//                    System.out.println(jsonObject.getString("secondTitle"));
//                    yzyh.setSecondTitle(jsonObject.getString("secondTitle"));
//                    System.out.println(jsonObject.getString("content"));
//                    yzyh.setContent(jsonObject.getString("content"));
//                    yzyh.setSearchTimes(0);
//                    yzyh.setHaveImage(false);
//                    yzyh.setLastSearch(new Date());
//                    yzyhService.save(yzyh);
//                }
            }catch (Exception e) {
//                System.out.print("error---->"+filePath);
                e.printStackTrace();
                return null;
            }
        return R.ok("欢迎使用springboot");

    }
    private  void readerMethod(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        JSONArray jsonArray = JSONArray.parseArray(sb.toString());
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject =(JSONObject) jsonArray.get(i);
            System.out.println(jsonObject);
            JiDianShouCe jdsc = new JiDianShouCe();
            jdsc.setFirstTitle(jsonObject.getString("firstTitle"));
            jdsc.setSecondTitle(jsonObject.getString("secondTitle"));
            jdsc.setContent(jsonObject.getString("content"));
            jdsc.setHaveImage(Boolean.getBoolean(jsonObject.getString("haveImage")));
            jdsc.setImageName(jsonObject.getString("ImageName"));
            float f=Float.parseFloat(jsonObject.getString("seachTimes"));
            // System.out.println(Integer.valueOf(jsonObject.getString("seachTimes")));
            jdsc.setSearchTimes((int)f);
            jdsc.setLastSearch(new Date());
            jdscService.save(jdsc);
        }
    }
//    public List<String> readWord(String filePath) throws Exception{
//        List<String> linList = new ArrayList<String>();
//        String buffer = "";
//        try {
//            if (filePath.endsWith(".doc")) {
//                InputStream is = new FileInputStream(new File(filePath));
//                WordExtractor ex = new WordExtractor(is);
//                buffer = ex.getText();
//                ex.close();
//                if(buffer.length() > 0){
//                    //使用回车换行符分割字符串
//                    String [] arry = buffer.split("\\r\\n");
//                    for (String string : arry) {
//                        linList.add(string.trim());
//                    }
//                }
//            } else if (filePath.endsWith(".docx")) {
//                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
//                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
//                buffer = extractor.getText();
//                extractor.close();
//                if(buffer.length() > 0){
//                    //使用换行符分割字符串
//                    String [] arry = buffer.split("\\n");
//                    for (String string : arry) {
//                        linList.add(string.trim());
//                    }
//                }
//            } else {
//                return null;
//            }
//            return linList;
//        } catch (Exception e) {
//            System.out.print("error---->"+filePath);
//            e.printStackTrace();
//            return null;
//        }
//    }


}



