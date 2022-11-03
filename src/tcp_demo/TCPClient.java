package tcp_demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author song
 * @date 2022/11/2 18:42
 */
public class TCPClient {
    public static void main(String[] args) {
        //tcp客户端
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("127.0.0.1", 10001);
             PrintWriter out =
                     new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),
                             StandardCharsets.UTF_8), true);
             Scanner in = new Scanner(new InputStreamReader(socket.getInputStream(),
                     StandardCharsets.UTF_8));
        ) {
            System.out.println("客户端启动成功");
            new Thread(() -> {
                while (in.hasNextLine()) {
                    String s = in.nextLine();
                    System.out.println("收到消息 = " + s);
                }
            }).start();
            //开启nagle算法
            socket.setTcpNoDelay(true);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println("发送的数据" + data);
                out.println(data);
                System.out.println("发送成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
