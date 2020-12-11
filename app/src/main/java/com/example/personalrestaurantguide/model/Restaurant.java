package com.example.personalrestaurantguide.model;

public class Restaurant {


    public Restaurant() {
    }

    public Restaurant(int restid, String name, String address, String city, String state, String zip, String country, String description, String tag, String phone, int active) {
        this.restid = restid;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.description = description;
        this.tag = tag;
        this.phone = phone;
        this.active = active;
    }

    int restid;
    String name;
    String address;
    String city;
    String state;
    String zip;
    String country;
    String description;
    String tag;
    String phone;
    int active;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    float rate;

    public int getRestid() {
        return restid;
    }

    public void setRestid(int restid) {
        this.restid = restid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
