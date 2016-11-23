package simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import netty.codec.MarshallingDecoder;
import netty.codec.MarshallingEncoder;
import netty.struct.Header;
import netty.struct.NettyMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akui on 16/11/23.
 */
public class Change {

    public static NettyMessage getMessage() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(123);
        header.setSessionID(99999);
        header.setType((byte) 1);
        header.setPriority((byte) 7);
        Map<String, Object> attachment = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            attachment.put("key:" + i, "value:" + i);
        }
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("abcdefgBBBDDDCCCCAAAAAA");
        return nettyMessage;
    }

    public static void testMessage() {
        try {
            ByteBuf buf = Unpooled.buffer();
            NettyMessage message = Change.getMessage();
            System.out.println(message);
            System.out.println("--------------");
            MarshallingEncoder.encode(message, buf);
            NettyMessage decodeMsg = (NettyMessage) MarshallingDecoder.decode(buf);
            System.out.println(decodeMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLong() {
        try {
            ByteBuf buf = Unpooled.buffer();
            long b = 0l;
            System.out.println(b);
            System.out.println("--------------");
            MarshallingEncoder.encode(b, buf);
            Object decodeMsg = MarshallingDecoder.decode(buf);
            System.out.println(decodeMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testByte() {
        try {
            ByteBuf buf = Unpooled.buffer();
            byte b = (byte) 0;
            System.out.println(b);
            System.out.println("--------------");
            MarshallingEncoder.encode(b, buf);
            byte decodeMsg = (byte)MarshallingDecoder.decode(buf);
            System.out.println(decodeMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Change.testByte();
    }
}
