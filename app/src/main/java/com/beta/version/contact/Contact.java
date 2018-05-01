package com.beta.version.contact;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by aa on 28/04/2018.
 */

public class Contact {


    private String id;
    private String fullname;
    private String type;
    private String num_tel;
    private String favoris = "0";

    public Contact() {
    }

    public Contact(String id, String fullname, String type, String num_tel, String favoris) {
        this.id = id;
        this.fullname = fullname;
        this.type = type;
        this.num_tel = num_tel;
        this.favoris = favoris;
    }

    public Contact(String id, String fullname, String type, String num_tel) {
        this.id = id;
        this.fullname = fullname;
        this.type = type;
        this.num_tel = num_tel;
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
        return "( " + this.getId() + " / " + this.fullname + " / " + this.type + " / " + this.getNum_tel() + " / " + this.getfavoris() + " )";
    }

    public String toString() {
        return "ID : " + this.id + "\nfullname : " + this.fullname + "\ntype : " + this.type + "\n num_tel : " + this.num_tel + "\n favoris : " + this.favoris;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getfavoris() {
        return favoris;
    }

    public void setfavoris(String favoris) {
        this.favoris = favoris;
    }

}
