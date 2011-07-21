package com.ensifera.animosity.craftirc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class MinecraftPoint implements EndPoint {

    Server server;
    MinecraftPoint(Server server) {
        this.server = server;
    }
    
    public Type getType() {
        return EndPoint.Type.MINECRAFT;
    }

    public void messageIn(RelayedMessage msg) {
        String message = msg.getMessage(this);
        for (Player p : server.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }
    
    public boolean userMessageIn(String username, RelayedMessage msg) {
        Player p = server.getPlayer(username);
        if (p == null) return false;
        p.sendMessage(msg.getMessage(this));
        return true;
    }
    
    public boolean adminMessageIn(RelayedMessage msg) {
        String message = msg.getMessage(this);
        boolean success = false;
        for (Player p : server.getOnlinePlayers())
            if (p.isOp()) {
                p.sendMessage(message);
                success = true;
            }
        return success;
    }
    
    public List<String> listUsers() {
        LinkedList<String> users = new LinkedList<String>();
        for (Player p : server.getOnlinePlayers()) {
            users.add(p.getName());
        }
        return users;
    }
    
    public List<String> listDisplayUsers() {
        LinkedList<String> users = new LinkedList<String>();
        for (Player p : server.getOnlinePlayers()) {
            users.add(p.getDisplayName());
        }
        Collections.sort(users);
        return users;  
    }

}