/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.struct.NettyMessage;

import java.util.Map;


public final class NettyMessageEncoder extends
        MessageToByteEncoder<NettyMessage> {

    private static final MarshallingEncoder marshallingEncoder = new MarshallingEncoder();


    public static void encode(ByteBuf sendBuf, NettyMessage msg) throws Exception {
        if (msg == null || msg.getHeader() == null)
            throw new Exception("The encode message is null");
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        sendBuf.writeInt((msg.getHeader().getLength()));
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        sendBuf.writeByte((msg.getHeader().getType()));
        sendBuf.writeByte((msg.getHeader().getPriority()));
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));
        //

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment()
                .entrySet()) {
            String key = param.getKey();
            byte[] keyArray = key.getBytes("UTF-8");
            int keyLen = keyArray.length;
//            System.out.println("encode key的长度为:" + keyLen);
            //写key大小
            sendBuf.writeInt(keyLen);
            //写入key
            sendBuf.writeBytes(keyArray);
            //得到value对象
            Object value = param.getValue();
            //写入value
            marshallingEncoder.encode(value, sendBuf);
        }

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg,
                          ByteBuf sendBuf) throws Exception {
        encode(sendBuf, msg);
    }
}
