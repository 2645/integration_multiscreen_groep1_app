package be.ehb.funinthequeue.model;

/**
 * Created by Dieter on 4/01/2016.
 */
public class User {
    private int id, balance, avatarId;
    private String fname, lname, mail, pw;

    public User(int id, int avatarId, int balance, String fname, String lname, String mail) {
        this.avatarId = avatarId;
        this.balance = balance;
        this.fname = fname;
        this.id = id;
        this.lname = lname;
        this.mail = mail;
    }

    public User(String fname, String lname, String mail, String pw) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.pw = pw;
    }

    public User(String mail, String pw) {
        this.mail = mail;
        this.pw = pw;
    }

    public User() {
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return fname + " " + lname;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof User) {
            User user = (User) o;
            if(this.id != 0) {
                return this.id == user.getId();

            } else if(this.fname != null & this.lname != null) {
                return this.fname.equals(user.getFname()) && this.lname.equals(user.getLname());

            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
