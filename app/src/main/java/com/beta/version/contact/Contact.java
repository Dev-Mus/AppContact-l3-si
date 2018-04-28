package com.beta.version.contact;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by aa on 28/04/2018.
 */

public class Contact {


    private int id;
    private String fullname;
    private String type;
    private String num_tel;
    private Date created_at;

    public Contact() {
    }

    public Contact(int id, String fullname, String type, String num_tel, Date created_at) {
        this.id = id;
        this.fullname = fullname;
        this.type = type;
        this.num_tel = num_tel;
        this.created_at = created_at;
    }

    public Contact(String fullname, String type, String num_tel) {
        this.fullname = fullname;
        this.type = type;
        this.num_tel = num_tel;
    }

    public String getStringContact() {
        return "( " + this.getFullname() + " / " + this.getType() + " / " + this.getNum_tel() + " )";
    }

    public String getStringContactComplete() {

        return "( " + this.getId() + " / "+ this.fullname + " / " + this.type + " / " + this.getNum_tel() +" / " + this.getCreated_at().toString() +" )";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
