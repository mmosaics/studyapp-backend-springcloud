package org.example;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.qos.server.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableDiscoveryClient
public class QuestionServiceApplication {

    public static void main(String[] args) {

        int defaultPort = 9040;
        int redisPort = 6379;
        int eurekaServerPort = 8848;

        int port = defaultPort;

        //配置dubbo.qos.port端口
        System.setProperty(com.alibaba.dubbo.common.Constants.QOS_PORT,"33333");
        //配置dubbo.qos.accept.foreign.ip是否关闭远程连接
        System.setProperty(Constants.ACCEPT_FOREIGN_IP,"false");

        new SpringApplicationBuilder(QuestionServiceApplication.class).properties("server.port=" + port).run(args);

        //close qos service
        Server.getInstance().stop();
    }

}
