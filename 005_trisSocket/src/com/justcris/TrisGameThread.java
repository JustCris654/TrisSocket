package com.justcris;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TrisGameThread extends Thread {
    private MatchInfo matchInfo;
    private Turn player;
    private PrintWriter output;
    private BufferedReader input;

    public TrisGameThread(MatchInfo matchInfo, Turn player) {
        this.matchInfo = matchInfo;
        this.player = player;
    }

    @Override
    public void run() {
        while(matchInfo.winner == null){
            while (player == matchInfo.turn){
                try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            }


        }

    }
}
