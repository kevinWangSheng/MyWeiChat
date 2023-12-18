## 基础逻辑
![img.png](/assert/imgs/archtech.png)
当发送消息，添加好友的时候，通过点击传递到客户端client，然后通过客户端发送对应的消息类型，比方说添加好友，发送消息，发送群消息等消息，然后在channel中进行对应的处理工作，在把他发送传递给服务器，服务器响应完成之后同样的发送对应的response类型传递回客户端。


启动工程：
```java
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
```
服务器和客户端启动的逻辑差不多类似，都是启动对应的BootStrap或者ServerBootStrap，然后获取他对应的channel，在通过channel判断他是否启动成功，并且使用定时任务，定时进行监听，类似ping的操作，如果发现他没有处于连接状态，那么就执行重新连接操作。

客户端或者服务器的逻辑类似
NettyClient：
```java
@Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new MyChannelInilizalier(uiService));
            future = bs.connect(inetHost, inetPort).syncUninterruptibly();
            channel = future.channel();
            BeanUtil.putBean("channel",channel);
        } catch (Exception e) {
            logger.error("socket client start error", e.getMessage());
        } finally {
            if(null != future || future.isSuccess()){
                logger.info("socket client start done. ");
            }else {
                logger.info("socket client start error. ");
            }
        }
        return  channel;
    }

    public void destory(){
        if(null == channel) return;

        group.shutdownGracefully();
        channel.close();
    }

    public boolean isActive(){
        return null != channel && channel.isActive();
    }

    public Channel channel(){
        return channel;
    }
```
大致上都是这个逻辑。
剩下的就是根据对应的业务进行处理业务逻辑了。