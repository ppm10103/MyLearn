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
package simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import netty.codec.NettyMessageDecoder;
import netty.codec.NettyMessageEncoder;
import netty.struct.Header;
import netty.struct.NettyMessage;

import java.util.HashMap;
import java.util.Map;


public class TestCodeC {


    public static NettyMessage getMessage() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(123);
        header.setSessionID(99999);
        header.setType((byte) 1);
        header.setPriority((byte) 7);
        Map<String, Object> attachment = new HashMap<String, Object>();
        for (int i = 0; i < 3; i++) {
            attachment.put("key:" + i, "value:" + i);
        }
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody((byte)0);
        return nettyMessage;
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        NettyMessage message = TestCodeC.getMessage();
        System.out.println(message);
        System.out.println("----------------------------");
        for (int i = 0; i < 5; i++) {
            ByteBuf buf = Unpooled.buffer();
            NettyMessageEncoder.encode(buf, message);
            NettyMessage decodeMsg = NettyMessageDecoder.decode(buf);
            System.out.println(decodeMsg);
            System.out.println("----------------------------");

        }

    }

}
