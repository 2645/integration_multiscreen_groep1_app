package be.ehb.funinthequeue.model;

/**
 * Created by Dieter on 4/01/2016.
 */
public class User {
    private int id;
    private String fname;
    private String lname;

    private String mail;
    private int balance;
    private int avatarId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public User() {
    }

    @Override
    public String toString() {
        return fname + " " + lname;
    }
}
