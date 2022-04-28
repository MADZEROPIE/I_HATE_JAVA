/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author MADZEROPIE
 */
public class MainServer {
    int port = 4510;
    InetAddress ip = null;
    int count = 0;
    ServerSocket ss;
    
    ServerModel model = BServerModel.build();
    public MainServer(){
         try {
            ip = InetAddress.getLocalHost();
          } catch (UnknownHostException ex) {
            System.out.println("Exception!");
         }
         try{
             System.out.println("Server Socket?");
            ss = new ServerSocket(port, 0 , ip);
            System.out.println("Server Schizophrenia now is ONLINE!");

            while(true)
            {
                Socket cs = ss.accept();
                System.out.println("Server Socket?");
                count++;
                ServerObserver so = new ServerObserver(cs, model); 
                model.addObserver(so);
            }
        
       } catch (IOException ex) {
           System.out.println("CAN'T CREATE FREAKING SERVER");
       }
    
    }
    public static void main(String[] args) {
        MainServer server = new MainServer();
    }
}
