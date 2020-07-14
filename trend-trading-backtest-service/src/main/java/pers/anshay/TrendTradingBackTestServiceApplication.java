package pers.anshay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author anshay
 * @date 2020/6/25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class TrendTradingBackTestServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TrendTradingBackTestServiceApplication.class).run(args);
    }
}
