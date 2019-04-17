package sample;

import java.net.*;
import java.io.*;


public class Client extends Thread
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private String address;
    private int port;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        this.address = address;
        this.port = port;

    }

    public void connect(){
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }


    public String sendMessage(String message){
        String response = "";
        try
        {
            out.writeUTF(message);
            response = input.readUTF();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
        return response;
    }


    public void disconnect(){
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }
        //client client = new client(); for the submit button

}