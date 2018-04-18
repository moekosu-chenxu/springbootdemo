package com.moekosu.testspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class App {

    // 入口
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }


}
