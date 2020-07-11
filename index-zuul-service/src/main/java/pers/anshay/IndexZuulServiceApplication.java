package pers.anshay;

import cn.hutool.core.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author anshay
 * @date 2020/6/25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableDiscoveryClient
@Slf4j
public class IndexZuulServiceApplication {

    public static void main(String[] args) {
        // int port = 8031;
        // if (!NetUtil.isUsableLocalPort(port)) {
        //     log.info("端口{}被占用了， 无法启动", port);
        //     System.exit(1);
        // }
        // new SpringApplicationBuilder(IndexZuulServiceApplication.class).properties("server.port=" + port).run(args);
        new SpringApplicationBuilder(IndexZuulServiceApplication.class).run(args);
    }
}
