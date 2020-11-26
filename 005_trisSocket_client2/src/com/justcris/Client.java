package com.justcris;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAdress = "127.0.0.1"; // ip localhost

        Socket connectionClient = new Socket(serverAdress, 2467);

        BufferedReader input = new BufferedReader(new InputStreamReader(connectionClient.getInputStream()));
        PrintWriter output = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(connectionClient.getOutputStream())), true);
        // String message = input.readLine();
        // System.out.println(message);

        System.out.println(connectionClient);
        boolean connected = connectionClient.isConnected();
        System.out.println(connected);
        while (true) {

            if (connected != connectionClient.isConnected()) {
                System.out.println(connectionClient.isConnected());
                connected = connectionClient.isConnected();
            }
        }
    }
}