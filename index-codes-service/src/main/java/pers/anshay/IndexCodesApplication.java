package pers.anshay;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author anshay
 * @date 2020/6/25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@Slf4j
public class IndexCodesApplication {
    public static void main(String[] args) {
        // int port = 0;
        // int defaultPort = 8011;
        // int redisPort = 6379;
        // int eurekaServerPort = 8761;
        //
        // if (NetUtil.isUsableLocalPort(eurekaServerPort)) {
        //     log.info("检查到端口{}未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort);
        //     System.exit(1);
        // }
        //
        // if (NetUtil.isUsableLocalPort(redisPort)) {
        //     log.info("检查到端口{}未启用，判断 redis 服务器没有启动，本服务无法使用，故退出%n", redisPort);
        //     System.exit(1);
        // }
        //
        // if (null != args && 0 != args.length) {
        //     for (String arg : args) {
        //         if (arg.startsWith("port=")) {
        //             String strPort = StrUtil.subAfter(arg, "port=", true);
        //             if (NumberUtil.isNumber(strPort)) {
        //                 port = Convert.toInt(strPort);
        //             }
        //         }
        //     }
        // }
        //
        // if (0 == port) {
        //     Future<Integer> future = ThreadUtil.execAsync(() -> {
        //         int p = 0;
        //         log.info("请于5秒钟内输入端口号, 推荐{} ,超过5秒将默认使用 {}", defaultPort, defaultPort);
        //         Scanner scanner = new Scanner(System.in);
        //         while (true) {
        //             String strPort = scanner.nextLine();
        //             if (!NumberUtil.isInteger(strPort)) {
        //                 System.err.println("只能是数字");
        //                 continue;
        //             } else {
        //                 p = Convert.toInt(strPort);
        //                 scanner.close();
        //                 break;
        //             }
        //         }
        //         return p;
        //     });
        //     try {
        //         port = future.get(5, TimeUnit.SECONDS);
        //     } catch (InterruptedException | ExecutionException | TimeoutException e) {
        //         port = defaultPort;
        //     }
        // }
        //
        // if (!NetUtil.isUsableLocalPort(port)) {
        //     log.info("端口{}被占用了，无法启动", port);
        //     System.exit(1);
        // }
        // new SpringApplicationBuilder(IndexCodesApplication.class).properties("server.port=" + port).run(args);
        new SpringApplicationBuilder(IndexCodesApplication.class).run(args);

    }
}
