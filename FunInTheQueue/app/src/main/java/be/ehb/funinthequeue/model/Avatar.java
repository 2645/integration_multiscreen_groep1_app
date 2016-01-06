package be.ehb.funinthequeue.model;

import android.graphics.Bitmap;

import be.ehb.funinthequeue.HelperFunctions;

/**
 * Created by Dieter on 4/01/2016.
 */

public class Avatar {
    private int id, price;
    private String name, img;
    private Bitmap bitmap;

    public Avatar(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Bitmap getBitmap() {
        if(bitmap == null) {
            this.bitmap = HelperFunctions.decodeBase64Image(img);
        }
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof Avatar) {
            Avatar avatar = (Avatar) o;
            if(this.id != 0) {
                return this.id == avatar.getId();

            } else if(this.name != null) {
                return this.name.equals(avatar.getName());

            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
