/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Bcast.IObserver;
import Bcast.MessageAction;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MADZEROPIE
 */
public class ServerObserver implements IObserver{
    Socket cs;
    DataInputStream dis;
    DataOutputStream dos;
    Gson convert = new Gson();
    Thread t;
    ServerModel m;

    public ServerObserver(Socket cs, ServerModel m) {
        this.cs = cs;
        this.m = m;
        System.out.println("Client has been connected");
        try {
            dos = new DataOutputStream(cs.getOutputStream());
            t = new Thread(
            ()->{
            try{
                dis = new DataInputStream(cs.getInputStream());
                while(true)
                {
                    String obj;
                    obj = dis.readUTF();
                    
                    Request r  = convert.fromJson(obj, Request.class);
                    System.out.print(r);
                    if (r.getAction() == MessageAction.ADD)
                        m.save(r.getMessage());
                    else if (r.getAction() == MessageAction.DELETE)
                        m.remove(r.getMessage());
                    else if (r.getAction() == MessageAction.EDIT)
                                            m.edit(r.getMessage());
                    
                    
                }
            } catch(IOException ex) {
                
            }
        }
      );
      t.start();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        Respond resp = new Respond(m.get());
        System.out.println("Updating: " + resp);
        String s = convert.toJson(resp);
        if (dos != null){
        try{
            dos.writeUTF(s);
        }   catch (IOException ex) {
                Logger.getLogger(ServerObserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("dos is null");    
        }
    }
    
    
}
