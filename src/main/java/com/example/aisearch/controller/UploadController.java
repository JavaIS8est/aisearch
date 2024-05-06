package com.example.aisearch.controller;

import com.example.aisearch.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：韦作旭
 * 时间：2024-05-06 下午9:17
 * 描述：文件上传控制层
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/uploadjdsc")
    public R UploadPicture(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String,Object> map =new HashMap<>();
        if(file.isEmpty()){
            // 这里是我自定义的异常，可省略
           return R.error("上传异常");
        }
        // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
        File savePos = new File("src/main/resources/static/images/jdsc");
        if(!savePos.exists()){  // 不存在，则创建该文件夹
            savePos.mkdir();
        }
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();
        // 上传该文件/图像至该文件夹下
        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));
        map.put("imageName",file.getOriginalFilename());
        map.put("msg","上传成功！");
        //attributes.addFlashAttribute("message","添加成功！");
        return R.ok(map);
    }


}
