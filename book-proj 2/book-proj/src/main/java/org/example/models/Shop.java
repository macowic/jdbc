package org.example.models;

public class Shop {
    private int id;
    private String name;
    private long latitude;
    private long longitude;


    public Shop() {

    }
    public Shop(int id, String name, long latitude, long longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Shop{" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }


}
