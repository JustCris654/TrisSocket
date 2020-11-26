package com.justcris;

public class MatchInfo {

    public volatile Turn turn;

    public int[][] matrix;

    public Turn winner;

    public MatchInfo() {
        this.turn = Turn.player1;
        this.matrix = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.winner = null;
    }
}

