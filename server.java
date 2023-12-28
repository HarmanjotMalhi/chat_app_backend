import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class server {
    public static void main(String[] args) throws IOException {

        ServerSocket so = new ServerSocket(12346);


        ArrayList<serverthreads> clientList = new ArrayList<>();

        while(true) {
            Socket ss = so.accept();
            serverthreads st = new serverthreads(ss,clientList);

            clientList.add(st);
            st.start();

            System.out.println("connected");

        }
    }
}
