package com.example.lab9;

public class Buiness {

    private String index;
    private String imageurl;
    private String name;
    private String rate;
    private String distance;
    private String id;
    public Buiness(){}
    public Buiness(String index,String imageurl,String name,String rate,String distance,String id){
        this.index=index;
        this.imageurl=imageurl;
        this.name=name;
        this.rate=rate;
        this.distance=distance;
        this.id=id;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
