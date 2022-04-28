/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Bcast.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MADZEROPIE
 */
public class Respond {
    ArrayList<Message> mesarr = new ArrayList<>();

    public ArrayList<Message> getMes() {
        return mesarr;
    }

    @Override
    public String toString() {
        // TODO: CHANGE TO FOR BY el in mesarr
        String s="";
        for (Message el : mesarr){
            s+=el.toString();
            s+=" ";
        }
        
        return "Respond{" + s + '}';
    }

    public void setMes(ArrayList<Message> mes) {
        this.mesarr = mes;
    }
    
    public Respond(List<Message> messages){
        this.mesarr.addAll(messages);
    }
    
    
}
