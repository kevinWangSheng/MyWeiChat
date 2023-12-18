package com.kevin.chat.codec;

import com.kevin.chat.protocol.Packet;
import com.kevin.chat.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wang
 * @create 2023-12-18-19:58
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {
    /**
     * 按照对应的规则进行解析，按照长度、命令、数据的顺序依次读取
     * @param channelHandlerContext
     * @param packet
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        byte[] data = SerializationUtil.serialize(packet);
        byteBuf.writeInt(data.length+1);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeBytes(data);
    }
}
