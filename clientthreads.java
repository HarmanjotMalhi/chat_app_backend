import java.net.*;
import java.io.*;
import java.util.*;

public class clientthreads extends Thread {

    static Socket sok;

    static InputStreamReader ir;

    static BufferedReader br;



    public clientthreads(Socket so) throws IOException {
        sok = so;
        try {
            ir = new InputStreamReader(sok.getInputStream());
            br = new BufferedReader(ir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //this while loop is used for continuously taking the input from the socket
        while(true) {
            String rr = null;
            try {
                rr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //decoding the byte from base64 to readable
            byte[] actualByte = Base64.getDecoder().decode(rr);
            //making a string from that byte
            String decodes = new String(actualByte);
            System.out.println(decodes);
        }

    }
}



