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

import com.caucho.hessian.io.HessianInput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;


public class MarshallingDecoder {


    public static Object decode(ByteBuf in) throws Exception {
        //得到对象大小
        ByteBufInputStream is = null;
        Object obj = null;
        try {
            int objectSize = in.readInt();
//            System.out.println("decode 对象大小为:" + objectSize);
            ByteBuf buf = in.slice(in.readerIndex(), objectSize);
            is = new ByteBufInputStream(buf);
            HessianInput hi = new HessianInput(is);
            obj = hi.readObject();
            in.readerIndex(in.readerIndex() + objectSize);
        } finally {
            is.close();
        }
        return obj;
    }
}