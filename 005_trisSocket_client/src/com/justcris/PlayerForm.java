package com.justcris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class PlayerForm {
    private JPanel trisFormPanel;
    private JButton btn_connect;
    private JButton btn_1;
    private JButton btn_4;
    private JButton btn_7;
    private JButton btn_2;
    private JButton btn_5;
    private JButton btn_8;
    private JButton btn_3;
    private JButton btn_6;
    private JButton btn_9;

    private ArrayList<JButton> buttons = new ArrayList<>() {{
        add(btn_1);
        add(btn_2);
        add(btn_3);
        add(btn_4);
        add(btn_5);
        add(btn_6);
        add(btn_7);
        add(btn_8);
        add(btn_9);
    }};
    private JLabel lbl_player;
    private JLabel lbl_turn;

    private String serverAddress;
    private Socket connectionClient;
    protected BufferedReader input;
    protected PrintWriter output;

    //Player id
    protected Player playerID;
    private String playerSymbol;

    protected Player turn;

    //player connection status
    private boolean connected;

    //Strings for receiving messages and respond to them
    protected String message;             //input message from server
    protected String response;            //respond to server

    //player thread that manage the comunication with the server
    private PlayerThread playerThread;

    //player move
    protected int playerMove;

    protected int[][] matrix_client = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};


    public PlayerForm() {

        connected = false;
        playerThread = new PlayerThread(this);
        playerMove = -1;

        btn_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (connected)
                    return;
                else{
                    int port = 2467;
                    ////System.out.println(args[0]+"\n"+args[1]);             //stampa 2467 e 4123 //old code
                    serverAddress = "127.0.0.1";   //ip localhost

                    try {
                        connectionClient = new Socket(serverAddress, port);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    try {
                        input = new BufferedReader(new InputStreamReader(connectionClient.getInputStream()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionClient.getOutputStream())), true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    try {
                        message = input.readLine();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    if (!message.equals("connected")) {
                        try {
                            connectionClient.close();               //disconnect from the occupied port and reconnect to server in another port
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            connectionClient = new Socket(serverAddress, Integer.parseInt(message));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionClient.getOutputStream())), true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            input = new BufferedReader(new InputStreamReader(connectionClient.getInputStream()));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        playerID = Player.player2;
                    } else {
                        playerID = Player.player1;
                    }

                    lbl_player.setText(playerID == Player.player1 ? "Player: 1 Symbol: O" : "Player: 2 Symbol: X");
                    playerSymbol = (playerID == Player.player1 ? "O" : "X");
                    System.out.println(playerID);
                    connected = true;
                    playerThread.start();
                }
            }
        });

        //click button 1
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_1.setText(playerSymbol);
                btn_1.setEnabled(false);
                playerMove = 1;
            }
        });

        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_2.setText(playerSymbol);
                btn_2.setEnabled(false);
                playerMove = 2;
            }
        });

        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_3.setText(playerSymbol);
                btn_3.setEnabled(false);
                playerMove = 3;
            }
        });

        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_4.setText(playerSymbol);
                btn_4.setEnabled(false);
                playerMove = 4;
            }
        });

        btn_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_5.setText(playerSymbol);
                btn_5.setEnabled(false);
                playerMove = 5;
            }
        });

        btn_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_6.setText(playerSymbol);
                btn_6.setEnabled(false);
                playerMove = 6;
            }
        });

        btn_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_7.setText(playerSymbol);
                btn_7.setEnabled(false);
                playerMove = 7;
            }
        });

        btn_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_8.setText(playerSymbol);
                btn_8.setEnabled(false);
                playerMove = 8;
            }
        });

        btn_9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn != playerID) {
                    return;
                }
                btn_9.setText(playerSymbol);
                btn_9.setEnabled(false);
                playerMove = 9;
            }
        });
    }

//    public int getInput(){
//
//    }



    public void setMove(int move) {
        buttons.get(move - 1).setText(playerID == Player.player1 ? "X" : "O");
        buttons.get(move - 1).setEnabled(false);
    }

    public int getPlayerMove() {
        return playerMove;
    }

    public void resetPlayerMove() {
        playerMove = -1;
    }

    public void setLabelTurn(boolean turn){
        if(turn){
            lbl_turn.setText("It's your turn");
        }else{
            lbl_turn.setText("It's yout opponent turn");
        }
    }

    public void setLabelWinner(String text){
        lbl_turn.setText(text);
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Tris");
        frame.setContentPane(new PlayerForm().trisFormPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
