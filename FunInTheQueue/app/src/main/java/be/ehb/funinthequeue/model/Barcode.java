package be.ehb.funinthequeue.model;

import java.sql.Date;

/**
 * Created by Dieter on 4/01/2016.
 */
public class Barcode {
    private int reward;
    private String id;
    private Boolean scanned;
    private Date date;

    public Barcode(){
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Boolean getScanned() {
        return scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }
}
