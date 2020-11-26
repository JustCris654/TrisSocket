package com.justcris;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TrisMatchHandler extends Thread{
    private final Socket client_1;
    private final Socket client_2;

    //private int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};


    private MatchInfo matchInfo;

    public TrisMatchHandler(Socket client_1, Socket client_2) {
        this.client_1 = client_1;
        this.client_2 = client_2;
        matchInfo = new MatchInfo();
    }

    @Override
    public void run() {
        TrisGameThread gameThreadClient_1;
        TrisGameThread gameThreadClient_2;

        gameThreadClient_1 = new TrisGameThread(this.matchInfo, Turn.player1);
        gameThreadClient_2 = new TrisGameThread(this.matchInfo, Turn.player2);
    }
}
