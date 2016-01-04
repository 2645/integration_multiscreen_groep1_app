package be.ehb.funinthequeue.model;

import java.sql.Date;

/**
 * Created by Dieter on 4/01/2016.
 */
public class Barcode {
    private int barcodeId, userId;
    private String rawdata;
    public java.sql.Date date;

    public Barcode(){
    }

    public int getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(int barcodeId) {
        this.barcodeId = barcodeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRawdata() {
        return rawdata;
    }

    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
