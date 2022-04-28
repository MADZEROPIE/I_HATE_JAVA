/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Bcast.MessageAction;
import Bcast.Message;

/**
 *
 * @author MADZEROPIE
 */
public class Request {

    public Message getMessage() {
        return message;
    }

    public MessageAction getAction() {
        return act;
    }

    public Request(Message message, MessageAction act) {
        this.message = message;
        this.act = act;
    }
    Message message;
    MessageAction act;

    @Override
    public String toString() {
        return "Request{" + "message=" + message + ", act=" + act + '}';
    }
    
    
}
