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

import com.caucho.hessian.io.HessianOutput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandler.Sharable;


@Sharable
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];


    public static void encode(Object msg, ByteBuf out) throws Exception {
        int initPos = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
        ByteBufOutputStream os = new ByteBufOutputStream(out);
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(msg);
        int objectSize = out.writerIndex() - initPos - 4;
        System.out.println("encode 对象大小为:" + objectSize);
        out.setInt(initPos, objectSize);
    }
}
