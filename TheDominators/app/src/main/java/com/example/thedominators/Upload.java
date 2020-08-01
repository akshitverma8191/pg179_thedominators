package com.example.thedominators;

public class Upload {

    String name;
    String imgurl;
    String city;
    String about;
    String longi;
    String lati;
    String river;

    public Upload() {

    }

    public Upload(String name, String imgurl, String city, String about, String longi, String lati,String river) {
        this.name = name;
        this.imgurl = imgurl;
        this.city = city;
        this.about = about;
        this.longi = longi;
        this.lati = lati;
        this.river=river;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }
}




