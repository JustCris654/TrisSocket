package com.justcris;

import java.util.Random;

public class MatchInfo {

    public volatile Player turn;

    public int[][] matrix;

    public Player winner;

    public MatchInfo() {
        this.turn = Player.player1;
        this.matrix = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        this.winner = null;
        Random rnd = new Random();
        this.turn = (rnd.nextInt(2) == 0 ? Player.player1 : Player.player2);
    }

    public void nextTurn() {
        if (this.turn == Player.player1) {
            this.turn = Player.player2;
        } else {
            this.turn = Player.player1;
        }
    }

    public boolean makeMove(int move) {
        int player = turn == Player.player1 ? 1 : 2;
        move -= 1;
        int x = ((int) (move / 3));
        int y = ((int) (move % 3));

        if (this.matrix[x][y] == 0) {
            this.matrix[x][y] = player;
            return true;
        } else {
            return false;
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    //controllo se qualcuno ha vinto
    // public boolean checkMatch(){

    // }

}
