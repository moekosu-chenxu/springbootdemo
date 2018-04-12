package com.moekosu.testspringboot;


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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.moekosu.dao"})
@ComponentScan(basePackages = {"com.moekosu.service"})
public class App {

//    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final ServerLogger logger = ServerLoggerFactory.getInstance();

    @Autowired
    private ImageService imageService;

    // 入口
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    // 
    @RequestMapping(value="/upload", method= {RequestMethod.GET,RequestMethod.POST},
                    consumes = "application/json", produces = "application/json")
    @ResponseBody
    public void uploadFile()
    {
        logger.debug("----------test-----------");
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
        // 初始化
        String ua = req.getHeader("User-Agent");
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
