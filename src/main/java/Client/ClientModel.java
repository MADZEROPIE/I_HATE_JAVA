/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Bcast.IObserver;
import Bcast.Message;
import Bcast.MessageAction;
import Server.Request;
import Server.Respond;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MADZEROPIE
 */
public class ClientModel {
    int port = 4510;
    InetAddress ip = null;
    Socket cs;
    
    DataInputStream dis;
    DataOutputStream dos;
    
    Gson convert = new Gson();
    
    Thread t;
    
    ArrayList<IObserver> all_obs = new ArrayList<>();
    ArrayList<Message> mes = new ArrayList<>();
    
    public void update(){
        for(IObserver obs : all_obs){
            obs.update();
        }
    } 
    
    public ClientModel(){
        
        try {
            ip = InetAddress.getLocalHost();
          } catch (UnknownHostException ex) {
            System.out.println("Exception!");
         }
        try {
            cs = new Socket(ip, port);
            dos = new DataOutputStream(cs.getOutputStream());
            t = new Thread(
            ()->{
                try{
                    dis = new DataInputStream(cs.getInputStream());
                    String obj;
                    Respond resp;
                    while(true)
                    {
                        obj = dis.readUTF();
                        resp = convert.fromJson(obj, Respond.class);
                        System.out.println("Get respond: " + resp);
                        mes = resp.getMes();
                        update();


                    }
                    } catch(IOException ex) {

                    }
                }
            );
            t.start();

        } catch(IOException ex){}
    }
    
    public void save (Message mess){
        Request req = new Request(mess, MessageAction.ADD);
        String send_str = convert.toJson(req);
        try{
            dos.writeUTF(send_str);
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove (Message mess){
        System.out.println("CM_REM: "+ mess);
        Request req = new Request(mess, MessageAction.DELETE);
        String send_str = convert.toJson(req);
        try{
            dos.writeUTF(send_str);
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edit (Message mess){
        System.out.println("CM_REM: "+ mess);
        Request req = new Request(mess, MessageAction.EDIT);
        String send_str = convert.toJson(req);
        try{
            dos.writeUTF(send_str);
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ArrayList<Message> get(){
        return mes;
    }
    
    public void addObserver(IObserver obs){
        all_obs.add(obs);
        update();
    }
}
