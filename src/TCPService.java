


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author song
 * @date 2022/11/2 18:50
 */
public class TCPService {
    static final Pattern PATTERN = Pattern.compile("(\\d+)\\+(\\d+)");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(10001);
             Socket socket = serverSocket.accept();
             Scanner in = new Scanner(new InputStreamReader(socket.getInputStream(),
                     StandardCharsets.UTF_8));
             PrintWriter out =
                     new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),
                             StandardCharsets.UTF_8), true)) {

            System.out.println("tcp已建立");
            //开启nagle算法
            socket.setTcpNoDelay(true);


            while (in.hasNextLine()) {
                String data = in.nextLine();
                System.out.println("data" + data);
                Matcher matcher = PATTERN.matcher(data);
                if (matcher.find()) {
                    int i1 = Integer.parseInt(matcher.group(1));
                    int i2 = Integer.parseInt(matcher.group(2));
                    out.println((i1 + i2));
                } else {
                    out.println(0);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
