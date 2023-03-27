package sample;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class clientSide {
    public static void main(String args[]) {
        try {
            ServerSocket ss = null;
            Socket s = null;
            final BufferedReader in;
            final PrintWriter out ;
            final Scanner sc = new Scanner(System.in);
            //ss = new ServerSocket(5001);
            s = new Socket("localhost",1222);
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            Thread envoyer = new Thread(new Runnable() {
                String message;

                @Override
                public void run() {
                    while (true) {
                        message = sc.nextLine();
                        out.println(message);
                        out.flush();
                    }
                }
            });
            envoyer.start();
            Thread recevoir = new Thread(new Runnable() {
                String msg = in.readLine();

                @Override
                public void run() {
                    while (msg != null) {
                        System.out.println("Serveur: " + msg);
                        try {
                            msg = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // out.close;
                    // sc.close;
                    // s.close();
                }
            });
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
