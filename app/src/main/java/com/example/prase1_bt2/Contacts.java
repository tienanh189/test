package com.example.prase1_bt2;

public class Contacts {
    private int Id;
    private String Name;
    private String Des;
    private int Image;
    private boolean status;

    public Contacts(int id, String name, String des, int image, boolean status) {
        Id = id;
        Name = name;
        Des = des;
        Image = image;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
