/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bcast;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author MADZEROPIE
 */
@Entity
@Table (name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    @Column (name = "task")
    String task;


    
   public Message(){}

   public Message(String task) {
        this.task = task;
   }
   
   public int getId() {
        return id;
    }

   public void setId(int id) {
        this.id = id;
    }

   public String getTask() {
        return task;
    }

   public void setTask(String task) {
        this.task = task;
    }
    
   @Override
   public String toString(){
    return "id = "+id+", task = "+ task;
   }
}
