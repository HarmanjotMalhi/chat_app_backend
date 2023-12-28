import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {

        //opening a socket for communication
        Socket so = new Socket("localhost",12346);

        PrintWriter pr = new PrintWriter(so.getOutputStream(),true);


        Scanner sc = new Scanner(System.in);
        //this string is used to accept the message that the user
        //wants to send to the other chatters
        String roro;

        //creating a clientthreads object and starting a thread which is used
        //to read the incoming input from other users via the server
        clientthreads ct = new clientthreads(so);
        ct.start();

        Scanner sci = new Scanner(System.in);

        //asking the user for a username for the chatroom
        System.out.print("Enter a username for the chat room: ");
        String userInput = sci.nextLine();
        String converted;

        while(true) {
            //reading the string the user wants to put in the chatroom
            roro = sc.nextLine();
            //when sent quit, the user exits the chat room
            if(roro.equals("quit")) break;
            //text replacements
            roro = replace_short(roro, userInput);
            roro = userInput + ": " + roro;
            //encoding the string in base64
            converted = Base64.getEncoder().encodeToString(roro.getBytes());
            pr.println(converted);
            pr.flush();
        }
    }

    /*this method performs the replacements in the string typed by the user before
    sending it to the other people in the chat room. Basically it scans the entire
    string for strings similar to /me, /books, /school and replace them with associated
    strings.
     */
    public static String replace_short(String input, String usrname){
        //returning string
        String retur = input;
        String test;
        //for loop used to iterate through every char of the string
        for (int i = 0; i < input.length(); i++) {

            //if encountered '/' then first check to confirm "/me" if confirmed
            //then replace with associated string
            if(input.charAt(i) == '/'){
                test = String.valueOf(input.charAt(i)) + String.valueOf(input.charAt(i+1)) + String.valueOf(input.charAt(i+2));
                if (test.equals("/me")){
                    //for edge case where the "/me" is at the end
                    if(i+2 == input.length()-1){
                        retur = input.substring(0, i) + usrname;
                    }
                    //if in between the string then do this
                    else {
                        retur = input.substring(0, i) + usrname + input.substring(i+3, input.length() - 1);
                    }
                }


                else if(i+5 <= input.length()-1) {
                    test = test + input.charAt(i + 3) + input.charAt(i + 4) + input.charAt(i + 5);
                    if (test.equals("/books")) {
                        if(i+4 == input.length()-1){
                            retur = input.substring(0, i) + "age of vice, atomic habits, evelyn hugo";
                        }
                        else{
                            retur = input.substring(0, i) + "age of vice, atomic habits, evelyn hugo" + input.substring(i + 5, input.length() - 1);
                        }
                    }
                }

                else if(i+6 <= input.length()-1) {
                    test = test + input.charAt(i + 3) + input.charAt(i + 4) + input.charAt(i + 5) + input.charAt(i + 6);
                    if (test.equals("/school")) {
                        if(i+6 == input.length()-1){
                            retur = input.substring(0, i) + "brock university";
                        }
                        else{
                            retur = input.substring(0, i) + "brock university" + input.substring(i + 6, input.length() - 1);
                        }
                    }
                }
            test = null;
	    }
        }
        return retur;
    }
}


