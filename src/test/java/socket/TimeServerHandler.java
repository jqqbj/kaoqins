package socket;


import com.sun.org.apache.xml.internal.serializer.OutputPropertyUtils;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author JQQ
 * @Date 2019/1/17 16:47
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        //BufferedWriter writer = null;
        PrintWriter out = null;
        try {
             reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //writer =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             out = new PrintWriter(socket.getOutputStream());
             String line;
             while((line= reader.readLine()) != null){
                 System.out.println(line);
                 out.println(new Date());
             }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                reader.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
