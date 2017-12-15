package com.moekosu.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义bean 关联book.properties @Component注入
 */
@Component
@ConfigurationProperties(prefix = "book")
@PropertySource(value = "classpath:static/book.properties")
public class BookConstant {

    private String name;

    private String author;

    private String price;

    // get/set method
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }
}
