package be.ehb.funinthequeue.model;

/**
 * Created by Dieter on 4/01/2016.
 */
public class Friendship {
    private int id, fromId, toId;

    public Friendship(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

}
