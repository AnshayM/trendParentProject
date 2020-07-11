package pers.anshay;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 第三方数据服务
 *
 * @author anshay
 * @date 2020/6/10
 */

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication {
    public static void main(String[] args) {
        // int port = 8090;
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
        // if (!NetUtil.isUsableLocalPort(port)) {
        //     System.err.printf("端口%d被占用了，无法启动%n", port);
        //     System.exit(1);
        // }
        // new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port=" + port).run(args);
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).run(args);

    }

}