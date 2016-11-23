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
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import netty.struct.Header;
import netty.struct.NettyMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    private static final MarshallingDecoder marshallingDecoder = new MarshallingDecoder();


    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
                               int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);

    }

//    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
//                               int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip) {
//        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
//
//    }


    public static NettyMessage decode(ByteBuf frame) throws Exception {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
//        System.out.println("decode,header:" + header);
        int mapSize = frame.readInt();
        if (mapSize > 0) {
            Map<String, Object> attch = new HashMap<String, Object>(mapSize);
            for (int i = 0; i < mapSize; i++) {
                //得到key大小
                int keySize = frame.readInt();
//                System.out.println("decode key的长度为:" + keySize);
                //得到key内容
                byte[] keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                String key = new String(keyArray, "UTF-8");
                attch.put(key, marshallingDecoder.decode(frame));
            }
            header.setAttachment(attch);
        }
        if (frame.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(frame));
        }
        message.setHeader(header);
        return message;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
            throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        return decode(frame);
    }
}
