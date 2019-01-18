package socket;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

    public static void main(String[] args) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(8091);
//        Socket socket = null;
//        while (true){
//            socket = serverSocket.accept();
//            new Thread(new TimeServerHandler(socket)).run();
//        }

        String a = "A";
        System.out.println(a.length());
        System.out.println(a.getBytes().length);

        File file = new File("Hello1.txt");
        // 创建文件
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);
        // 向文件写入内容
        writer.write("tt我t");
        writer.flush();
        writer.close();
        // 创建 FileReader 对象
        FileReader fr = new FileReader(file);

        int ch = 0;
        while ((ch = fr.read()) != -1) //记住即可，read方法如果没有可读取的了，则返回-1，所以就是一直读取，并将
        //读取的内容存入ch，一直到结尾
        {
            System.out.println((char) ch);//打印读取的结果，由于ch是int类型，将其强制转换为String类型
        }


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.shutdown();

    }

}
