package com.justcris;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket_1 = new ServerSocket(2467);
        ServerSocket serverSocket_2 = new ServerSocket(4123);
        Socket receiveClient_1;
        Socket receiveClient_2;
        Socket receiveClient_1_ditroppo;

        System.out.println("Server online...");

        while (true) {
            receiveClient_1 = serverSocket_1.accept();

            //            System.out.println(receiveClient_1.toString());

            PrintWriter output_1 = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(receiveClient_1.getOutputStream())), true);
            output_1.println("connected");
            System.out.println(receiveClient_1 + "  e si è connesso");

            receiveClient_1_ditroppo = serverSocket_1.accept(); // riceve il secondo client 1 e gli dice che quella
                                                                // posizione li è gia occupata
                                                                //            System.out.println(receiveClient_1_ditroppo.toString());

            PrintWriter output_1_ditroppo = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(receiveClient_1_ditroppo.getOutputStream())), true);

            output_1_ditroppo.println("4123"); // riferisco che il client 1 è gia occupato dandogli la porta su cui
                                               // connettersi
            System.out.println(receiveClient_1_ditroppo + "  e gli ho detto di andarsene");

            receiveClient_2 = serverSocket_2.accept();
            System.out.println(receiveClient_2 + "   e si è connesso");

            TrisMatchHandler trisMatch = new TrisMatchHandler(receiveClient_1, receiveClient_2);
            trisMatch.start();
        }

    }
}
