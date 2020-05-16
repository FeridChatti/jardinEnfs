/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Date;

/**
 * @author karim
 */
public class Messages implements Externalizable {
    private int id;


    private String date;


    private String msg;
    private String jarname;

    private String sendername;


    private Jardin jardin;

    private Parents parent;

    private User sender;

    public Messages() {
    }


    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public Messages(int id, String date, String msg, Jardin jardin, Parents parent, User sender) {
        this.id = id;
        this.date = date;
        this.msg = msg;
        this.jardin = jardin;
        this.parent = parent;
        this.sender = sender;
    }

    public String getJarname() {
        return jarname;
    }

    public void setJarname(String jarname) {
        this.jarname = jarname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

    public Parents getParent() {
        return parent;
    }

    public void setParent(Parents parent) {
        this.parent = parent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        out.writeLong(Long.parseLong(date.toString()));
        Util.writeUTF(sendername, out);
        Util.writeUTF(String.valueOf(sender.getId()), out);

        Util.writeUTF(jarname, out);
        Util.writeUTF(msg, out);

    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {

    }

    @Override
    public String getObjectId() {
        return null;
    }
}
