package com.justcris;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket_1 = new ServerSocket(2467);
        ServerSocket serverSocket_2 = new ServerSocket(4123);

        System.out.println("Server online...");

        while (true) {
            Socket receiveClient_1 = serverSocket_1.accept();

            //Socket receiveClient_2 = serverSocket_2.accept();

            System.out.println(receiveClient_1 + "\n\n");

            //TrisMatchHandler trisMatch = new TrisMatchHandler(receiveClient_1, receiveClient_2);
            //trisMatch.start();
        }
    }
}
