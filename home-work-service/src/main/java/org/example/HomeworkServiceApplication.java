package org.example;

import com.alibaba.dubbo.common.Constants;
import org.apache.dubbo.qos.server.Server;
import org.example.service.HomeworkService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class HomeworkServiceApplication {
    public static void main(String[] args) {
        int defaultPort = 9070;
        int redisPort = 6379;
        int eurekaServerPort = 8848;
        int port = defaultPort;



        //配置dubbo.qos.port端口
        System.setProperty(com.alibaba.dubbo.common.Constants.QOS_PORT,"33333");
        //配置dubbo.qos.accept.foreign.ip是否关闭远程连接
        System.setProperty(Constants.ACCEPT_FOREIGN_IP,"false");

        new SpringApplicationBuilder(HomeworkServiceApplication.class).properties("server.port=" + port).run(args);

        //close qos service
        Server.getInstance().stop();
    }
}
