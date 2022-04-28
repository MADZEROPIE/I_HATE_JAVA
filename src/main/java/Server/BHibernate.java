/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Bcast.Message;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author MADZEROPIE
 */
public class BHibernate {
    private static SessionFactory sesFac;

    public static SessionFactory getSesFac() {
        if (sesFac == null){
            try {
                Configuration cnfg = new Configuration().configure("/hibernate.cfg.xml");
                cnfg.addAnnotatedClass(Message.class);
                StandardServiceRegistryBuilder bldr = new StandardServiceRegistryBuilder().applySettings(cnfg.getProperties());
                sesFac = cnfg.buildSessionFactory(bldr.build());
            } catch (Exception e){
                System.out.println("EXCEPTION HAS BEEN THROWED WHILE BUILDING FACTORY");
            }
        }
        return sesFac;
    }
    
}
