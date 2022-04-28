/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Bcast.IObserver;
import Bcast.Message;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MADZEROPIE
 */
public class ServerModel {
    ArrayList<IObserver> all_obs = new ArrayList<>();
    
    void update(){
        for (IObserver obs: all_obs){
            obs.update();
        }
    }
    public ServerModel() {
        Session session = BHibernate.getSesFac().openSession();
        session.close();
    
    }
    
    public void save (Message mes){
        System.out.println("trying to add message: " + mes);
        Session session = BHibernate.getSesFac().openSession();
        Transaction trans = session.beginTransaction();
        session.save(mes);
        trans.commit();
        session.close();
        update();
    }
    
    public void remove (Message mes){
        System.out.println("trying to REMOVE message: " + mes);
        Session session = BHibernate.getSesFac().openSession();
        Transaction trans = session.beginTransaction();
        session.remove(mes);
        trans.commit();
        session.close();
        update();
    }
    
    List<Message> get(){
        List<Message> msg = (List<Message>)BHibernate.getSesFac().openSession().createQuery("From Message").list();
        return msg;
    }
    
    public void addObserver (IObserver obs){
        all_obs.add(obs);
        update();
    }
    
}
