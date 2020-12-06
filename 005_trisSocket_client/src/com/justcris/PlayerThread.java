package com.justcris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerThread extends Thread {

    private final PlayerForm playerData;


    public PlayerThread(PlayerForm playerData) {
        this.playerData = playerData;
    }

    @Override
    public void run() {

        System.out.println("Client in ascolto");
        try {
            playerData.message = playerData.input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(playerData.message);

        boolean matchOver = false;

        while (!matchOver) {
            try {
                playerData.message = playerData.input.readLine();           //ricevo in input un messaggio dal server
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (playerData.message.equals("ha vinto 1") || playerData.message.equals("ha vinto 2")) {
                matchOver = true;
                playerData.setLabelWinner(playerData.message);
            } else {
                System.out.println(playerData.message);

                if (playerData.message.equals("turn")) {
                    playerData.turn = playerData.playerID;
                    playerData.setLabelTurn(true);

                    try {
                        playerData.message = playerData.input.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println(playerData.message);

                    if (!playerData.message.equals("first")) {
                        playerData.setMove(Integer.parseInt(playerData.message));
                    }

                    boolean result;

                    do {
                        playerData.turn = playerData.playerID;
                        //aspetto che il client fa la sua mossa
                        while (playerData.getPlayerMove() == -1) {
                            try {
                                sleep(202);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        playerData.turn = null;

                        System.out.println(playerData.getPlayerMove());

                        //quando ha fatto la sua mossa
                        //invio la mossa al server
                        playerData.output.println(playerData.playerMove);

                        try {
                            playerData.message = playerData.input.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println(playerData.message);

                        result = (playerData.message.equals("success"));

                        //resetto la mossa fatta dal giocatore
                        playerData.resetPlayerMove();
                    } while (!result);

                    playerData.turn = null;
                }
                playerData.setLabelTurn(false);

            }


        }

    }


}
