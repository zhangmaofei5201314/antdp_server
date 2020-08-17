package com.donbala;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2020/8/17
 */
@SpringBootApplication
@MapperScan("com.donbala.*.dao")
@ServletComponentScan
@EnableTransactionManagement
public class AntdpServerApplication {
    public static void main(String[] args) {
        System.out.println("programming launching");
        SpringApplication.run(AntdpServerApplication.class, args);
    }
}
