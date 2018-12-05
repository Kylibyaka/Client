

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("vm2.skycelot.ru", 6666));

        ByteBuffer buffer = ByteBuffer.allocate(20);
        for(byte value:"BOREYKO, ARTEM".getBytes(StandardCharsets.UTF_8)){
            buffer.put(value);
        }
        buffer.flip();
        int written = socketChannel.write(buffer);
        System.out.println(written);

        buffer.clear();
        int read = socketChannel.read(buffer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte inputValue;
        while((inputValue = buffer.get()) != -1){
            baos.write(inputValue);
        }
        System.out.println(new String(baos.toByteArray()));
    }
}

