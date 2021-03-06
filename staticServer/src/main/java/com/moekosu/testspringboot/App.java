package com.moekosu.testspringboot;


import com.alibaba.druid.util.StringUtils;
import com.moekosu.constant.Image;
import com.moekosu.logger.ServerLogger;
import com.moekosu.logger.ServerLoggerFactory;
import com.moekosu.service.ImageService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.moekosu.dao"})
@ComponentScan(basePackages = {"com.moekosu.service"})
public class App {

//    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final ServerLogger logger = ServerLoggerFactory.getInstance();
    // 图片格式
    private final String[] photo = new String[]{"jpg","png","gif"};

    @Autowired
    private ImageService imageService;

    // 入口
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    /**
     * 文件上传
     * @param file
     */
    @RequestMapping(value="/upload", method= {RequestMethod.GET,RequestMethod.POST},
                    consumes = "multipart/form-data",produces = "application/json")
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file)
    {
        logger.debug("upload file start");
        // 临时文件存在
        if(!file.isEmpty()){
            // 存放文件根目录
            String fileDir = "d://img";
            File dir = new File(fileDir);
            if(dir.isDirectory()){
                // 拼接文件存放最终目录
                String currFilepath = fileDir + "/" + file.getOriginalFilename();
                File currFile = new File(currFilepath);
                // 存放
                try{
                    file.transferTo(currFile);
                }
                catch (IOException e){
                    logger.error("upload file error.", e);
                    return "ERROR";
                }
                // 存放成功，保存数据库
                Image img = new Image();
                img.setName(file.getOriginalFilename());
                img.setPath(currFilepath);
                img.setStatus("1");
//                imageService.insert(img);
            }
        }
        return "SUCCESS";
    }

    /**
     * 多文件上传
     * @param req
     */
    @RequestMapping(value="/uploadMulti", method= {RequestMethod.GET,RequestMethod.POST},
            consumes = "multipart/form-data")
    public void uploadMultiFile(HttpServletRequest req)
    {
        List<MultipartFile> list = ((MultipartHttpServletRequest) req).getFiles("fileName");
    }

    /**
     * 展示静态图片
     * @param name 文件名，{name:.+}正则匹配.符号
     * @param req
     * @param resp
     */
    @RequestMapping(value="/{name:.+}", method= {RequestMethod.GET,RequestMethod.POST})
    public void showImage(@PathVariable String name,
                            HttpServletRequest req, HttpServletResponse resp)
    {
        logger.debug("get image start");
        //
        if(StringUtils.isEmpty(name)){
            logger.debug("no file name");
            return;
        }
        // 初始化
        String ua = req.getHeader("User-Agent");
        // 如果不是图片形式，则下载文件，如果是图片，则预览图片
        List<String> photoPrefix = Arrays.asList(photo);
        String[] ends = name.split(".");
        String end = ends[ends.length - 1];
        if(!photoPrefix.contains(end)){
            // 设置返回方式，返回attachment则表示下载文件
            resp.setCharacterEncoding("UTF-8");
            resp.addHeader("Content-Disposition", "attachment; filename=" + name);
        }
        // 获取图片对象
        Image img = new Image();
        img.setName(name);
//        img = imageService.getImageByKey(img);
        img.setPath("d://1.jpg");
        // 图片路径
        String path = img.getPath();
        try{
            File file = new File(path);
            if(file.exists()){
                // file文件获取输入流
                InputStream in = new FileInputStream(file);
                OutputStream out = resp.getOutputStream();
                byte[] buf = new byte[1024];
                int len = -1;
                // 写入输出流
                while((len = in.read(buf)) != -1){
                    out.write(buf, 0, len);
                    out.flush();
                }
                in.close();
                out.close();
            }
        }
        catch (FileNotFoundException e){
            logger.error("file not exist.", e);
        }
        catch (IOException e){
            logger.error("io exception, get resp stream error", e);
        }
        catch (Exception e){
            logger.error("exception.", e);
        }

        logger.debug("get image end");
    }

}
