import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class serverthreads extends Thread{

    ArrayList<serverthreads> threads;
    Socket socket;
    PrintWriter pw;

    public serverthreads(Socket socke, ArrayList<serverthreads> threadss){

        threads = threadss;
        socket = socke;
    }

    public void run() {

        try {
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(in);


            pw = new PrintWriter(socket.getOutputStream(), true);

            while(true) {
                String rr = br.readLine();
                for (serverthreads af : threads) {
                    af.pw.println(rr);
                }
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }}


