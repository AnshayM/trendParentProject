package pers.anshay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 视图启动类
 *
 * @author anshay
 * @date 2020/6/25
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class TrendTradingBackTestViewApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TrendTradingBackTestViewApplication.class).run(args);
    }
}
