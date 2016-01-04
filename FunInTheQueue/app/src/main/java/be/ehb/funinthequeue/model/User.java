package be.ehb.funinthequeue.model;

/**
 * Created by Dieter on 4/01/2016.
 */
public class User {
    private int user_id;
    private String user_firstname;
    private String user_lastname;
    private String user_mail;
    private int user_balance;
    private int user_currentavatar_id;

    public User() {
    }

    public int getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(int user_balance) {
        this.user_balance = user_balance;
    }

    public int getUser_currentavatar_id() {
        return user_currentavatar_id;
    }

    public void setUser_currentavatar_id(int user_currentavatar_id) {
        this.user_currentavatar_id = user_currentavatar_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    @Override
    public String toString() {
        return user_firstname + " " + user_lastname;
    }
}
