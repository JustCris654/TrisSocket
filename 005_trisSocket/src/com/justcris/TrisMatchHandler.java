package com.justcris;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class TrisMatchHandler extends Thread {
    private final Socket client_1;
    private final Socket client_2;

    private final PrintWriter output_client1;
    private final BufferedReader input_client1;

    private final PrintWriter output_client2;
    private final BufferedReader input_client2;

    private MatchInfo matchInfo;

    private boolean matchOver;

    private String lastMove;

    private String message; //message received from client
    private int move; //current move of the client

    public TrisMatchHandler(Socket client_1, Socket client_2) throws IOException {
        // client 1 = player 1
        this.client_1 = client_1;
        output_client1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client_1.getOutputStream())), true);
        input_client1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

        // client 2 = player 2
        this.client_2 = client_2;
        output_client2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client_2.getOutputStream())), true);
        input_client2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

        matchInfo = new MatchInfo();
        System.out.println(matchInfo.turn);

        matchOver = false;

        lastMove = "";
    }

    @Override
    public void run() {

        output_client1.println("Welcome to the match player 1");
        // System.out.println("Scrivo hello player 1 al client 1");
        output_client2.println("Welcome to the match player 2");

        while (!matchOver) { // il ciclo continua finchè il match non è concluso

            BufferedReader in;
            PrintWriter out;

            //attribuisco a in e out l'input e l'outèut al client che dovrà fare la mossa
            //in modo da non scrivere il codice 2 volte, uno per client
            if (matchInfo.turn == Player.player1) {
                in = input_client1;
                out = output_client1;
            } else {
                in = input_client2;
                out = output_client2;
            }

            out.println("turn"); //dico al client che è il suo turno
            if (!lastMove.equals("")) {
                out.println(lastMove); //gli mando l'ultima mossa fatta cosicchè possa aggiornare la sua matrice
            } else {
                out.println("first"); //se il gioco è appena iniziato gli dico che la su è la prima mossa
            }

            boolean result = false;

            do {
                try {
                    message = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(message);

                move = Integer.parseInt(message);

                result = matchInfo.makeMove(move);
                System.out.println("Mossa: " + move + ": " + result);

                out.println(result ? "success" : "notAllowed");
            } while (!result);

            lastMove = Integer.toString(move);

            matchInfo.nextTurn();

            // int[][] matrix = matchInfo.getMatrix();
            // System.out.println(Arrays.deepToString(matrix));
        }
    }

}
