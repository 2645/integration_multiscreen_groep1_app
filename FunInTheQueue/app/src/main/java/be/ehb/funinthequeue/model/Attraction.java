package be.ehb.funinthequeue.model;

/**
 * Created by Dieter on 4/01/2016.
 */
public class Attraction {
    private int id, queuetime;
    private String name, description, img;
    private float lat, lon;

    public Attraction(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueuetime() {
        return queuetime;
    }

    public void setQueuetime(int queuetime) {
        this.queuetime = queuetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
