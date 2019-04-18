package sample;

import java.net.*;
import java.io.*;


public class Client
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private String address;
    private int port;
    private String message, response;

    public interface ServerResponse{
        void response(String message);
    }

    private ServerResponse serverResponse;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        this.address = address;
        this.port = port;

    }

    public  void setServerResponse(ServerResponse serverResponse){
        this.serverResponse = serverResponse;
    }


    public void connect(){
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from socket
            input  = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }


    public void sendMessage(String message){

        try
        {
            out.writeUTF(message);
            System.out.println("message is sent");
            this.response = input.readUTF();
            System.out.println("response = "+this.response);
            serverResponse.response(this.response);
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
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