package com.daniel.nettys.nettyl.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import java.net.InetSocketAddress;

public class HttpServeTest {
    public void start(int port){
        EventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup workers = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(boss,workers)
              .channel(NioServerSocketChannel.class)
              .localAddress(new InetSocketAddress(port))
              .childHandler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  protected void initChannel(SocketChannel ch) {
                      ch.pipeline()
                              .addLast("codec", new HttpServerCodec()) // HTTP 编解码
                              .addLast("compressor", new HttpContentCompressor())// HttpContent 压缩
                              .addLast("aggregator", new HttpObjectAggregator(65536))// HTTP 消息聚合
                              .addLast("handler", new HttpServerHandler());
                  }
              }).childOption(ChannelOption.SO_KEEPALIVE,true);
        ChannelFuture f = null;
        try {
            f = server.bind().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Http Server started， Listening on " + port);
        try {
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        HttpServeTest httpServeTest = new HttpServeTest();
        httpServeTest.start(8801);
    }
}
