package com.kevin;

import com.kevin.applicaton.UIService;
import com.kevin.chat.protocol.login.ReconnectRequest;
import com.kevin.event.ChatEvent;
import com.kevin.event.LoginEvent;
import com.kevin.infrustruct.utils.CacheUtil;
import com.kevin.socket.NettyClient;
import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.chat.ChatController;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;
import org.itstack.naive.chat.ui.view.login.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class Application extends javafx.application.Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    private ScheduledExecutorService scheduledExecutorService =Executors.newScheduledThreadPool(1);

    @Override
    public void start(Stage stage) throws Exception {
        // 启动窗口
        IChatMethod chatMethod = new ChatController(new ChatEvent());
        ILoginMethod login = new LoginController(new LoginEvent(),chatMethod);
        login.doShow();

        UIService uiService = new UIService();
        uiService.setChatMethod(chatMethod);
        uiService.setLoginEvent(login);

        //启动socket 连接
        logger.info("start the client to connect");
        NettyClient client = new NettyClient(uiService);
        logger.info("the client's host is {},and the port is {}",client.inetHost,client.inetPort);
        Future<Channel> future = executorService.submit(client);
        Channel channel = future.get();
        if(null == channel){
            logger.error("the client start error");
        }
        // 进行连接等待
        if(!client.isActive()){
            logger.info("the client is connecting to the server ....");
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        }
        // 每5秒尝试判断一次连接是否存在
        scheduledExecutorService.scheduleAtFixedRate(()->{
            while(!client.isActive()){
                try {
                    logger.info("the client was not active ,reconecting...");
                    Channel freshChannel = executorService.submit(client).get();
                    if(null == CacheUtil.userId) continue;
                    freshChannel.writeAndFlush(new ReconnectRequest(CacheUtil.userId));
                    logger.info("reconnected success");
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("reconnected failure");
                }
            }
        },3,5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
