package com.example.lab9;

public class Reserve {
    private String index;
    private String name;
    private String date;
    private String time;
    private String email;
    public Reserve(){}
    public Reserve( String index, String name, String date, String time, String email){
        this.index=index;
        this.name=name;
        this.date=date;
        this.time=time;
        this.email=email;

    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
