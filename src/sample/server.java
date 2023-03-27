package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String args[]) {
        try {
            ServerSocket ss;
            final Socket s ;
            final BufferedReader in ;
            final PrintWriter out ;
            final Scanner sc = new Scanner(System.in);
            ss = new ServerSocket(1222);
            s = ss.accept();
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
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
                    try {
                        while (msg != null) {
                            System.out.println("Client: " + msg);

                            msg = in.readLine();

                        }
                        //  out.close;
                        // sc.close;
                        s.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}