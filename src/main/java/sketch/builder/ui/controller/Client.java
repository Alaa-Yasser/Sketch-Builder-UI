package  sketch.builder.ui.controller;

import java.net.*;
import java.io.*;


public class Client
{
    // initialize socket and input output streams
    private Socket socket            = null;
//    private InputStream  input   = null;
//    private OutputStream out     = null;
    private String address;
    private int port;
    private String message, response;

    public interface ServerResponse{
        void response(String message, String response);
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

        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }


    public void sendMessage(String message){

        try
        {
            connect();
            InputStream input  = socket.getInputStream();

            // sends output to the socket
            OutputStream out    = socket.getOutputStream();
            byte[] messageBytes = message.getBytes();
            out.write(messageBytes);
            System.out.println("message is sent");
//            InputStream inputStream = socket.getInputStream();
            byte[] mess = recvMsg(input);
            if(mess != null)
                this.response = new String((mess)).substring(0, 3);
            System.out.println("response = " + this.response+"_");
            if(!message.equals("exit"))
                serverResponse.response(message, this.response);
            input.close();
            out.close();
            disconnect();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }


    public void disconnect(){
        try
        {

            socket.close();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }
        //client client = new client(); for the submit button

    public static byte[] recvMsg(InputStream inpustream) {
        try {

            byte len[] = new byte[1024];
            int count = inpustream.read(len);

            byte[] temp = new byte[count];
            for (int i = 0; i < count; i++) {
                temp[i] = len[i];
            }
            return temp;
        } catch (Exception e) {
            System.out.println("recvMsg() occur exception!" + e.toString());
        }
        return null;
    }

}