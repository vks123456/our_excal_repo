package gawds.nitkkr.com.selleasy;

import android.util.Log;

import java.security.Timestamp;
import java.util.Date;

public class models {
    int pno;
    String pname;
    int price;
    String category;
    String name;
    long contact;
    String image;
    String username;
    Long time;

    public void setPno(int pno) {
        this.pno=pno;
    }

    public void setPname(String pname) {
        this.pname=pname;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPname() {
        return pname;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public long getContact() {
        return contact;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public long getTime() {
        return time;
    }

    public int getPno() {
        return pno;
    }


}

