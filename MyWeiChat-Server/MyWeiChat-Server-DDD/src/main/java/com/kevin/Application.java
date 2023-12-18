package com.kevin;

import com.kevin.socket.NettyServer;
import io.netty.channel.Channel;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
@ImportResource(locations = {"classpath:spring-config.xml"})
public class Application  extends SpringBootServletInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Resource
    private NettyServer nettyServer;
    @Override
    public void run(String... args) throws Exception {
        logger.info("the server is starting, and the host is {},port is {}","127.0.0.1",nettyServer.port);
        Future<Channel> future = executorService.submit(nettyServer);
        Channel channel = future.get();
        if(null == channel){
            throw new RuntimeException("the server was start and error,and the channel is null");
        }

        while(!channel.isActive()){
            logger.info("starting...");
            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
        }
        logger.info("start successful,and the host is :{}",channel.localAddress());

    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args )
    {
        SpringApplication.run(Application.class);
    }
}
