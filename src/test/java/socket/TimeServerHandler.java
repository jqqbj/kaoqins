package socket;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

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
