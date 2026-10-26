package top.fcidd.blog.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.fcidd.blog.api.mapper")
public class BlogApplication {

    static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
