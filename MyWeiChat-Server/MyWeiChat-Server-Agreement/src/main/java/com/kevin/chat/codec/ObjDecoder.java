package com.kevin.chat.codec;

import com.kevin.chat.protocol.Packet;
import com.kevin.chat.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author wang
 * @create 2023-12-18-20:24
 */
public class ObjDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() <4){
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if(in.readableBytes()<dataLength){
            in.resetReaderIndex();
            return;
        }
        byte command = in.readByte();
        byte[] data = new byte[dataLength-1];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, Packet.getPacket(command)));

    }
}
