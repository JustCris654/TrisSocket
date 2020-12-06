//package com.justcris;
//
//import java.io.*;
//import java.net.Socket;
//
//public class Client {
//    public static void main(String[] args) throws IOException {
//        //System.out.println(args[0]+"\n"+args[1]);             //stampa 2467 e 4123
//        String serverAdress = "127.0.0.1";   //ip localhost
//        Socket connectionClient = new Socket(serverAdress, Integer.parseInt(args[0]));
//
//        BufferedReader input = new BufferedReader(new InputStreamReader(connectionClient.getInputStream()));
//        PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionClient.getOutputStream())), true);
//
//        if(input.readLine().equals("occupied")){
//            connectionClient.close();
//            connectionClient = new Socket(serverAdress, Integer.parseInt(args[1]));
//            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionClient.getOutputStream())), true);
//            input = new BufferedReader(new InputStreamReader(connectionClient.getInputStream()));
//        }
//
//        while (true){
//
//        }
//
//
//
//
//
//    }
//}
