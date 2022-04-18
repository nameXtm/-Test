package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 * MultipartFile自动封装文件上传；
 */
@Slf4j
@Controller
public class FormTestController {

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,//MultipartFile自动封装文件上传；
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传信息： email={}",email,username,headerImg.getSize(),photos.length);

        //对内容进行保存
        if(!headerImg.isEmpty()){
            //保存到文件服务，oss服务器
            String originalFilename = headerImg.getOriginalFilename();

            headerImg.transferTo(new File("D:\\haha、、"+originalFilename));
        }
        if (photos.length>0){
            for (MultipartFile photo: photos
                 ) {
                if (!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("D:\\haha\\"+originalFilename));

                }

            }
        }

        return "main";
    }
}
