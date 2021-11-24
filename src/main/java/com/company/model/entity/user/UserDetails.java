package com.company.model.entity.user;

import com.company.model.entity.place.Place;

public class UserDetails {
    private int id;
    private String firstnameEn;
    private String lastnameEn;
    private String firstnameUa;
    private String lastnameUa;
    private String email;
    private String phone;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstnameEn() {
        return firstnameEn;
    }
    public void setFirstnameEn(String firstnameEn) {
        this.firstnameEn = firstnameEn;
    }

    public String getLastnameEn() {
        return lastnameEn;
    }
    public void setLastnameEn(String lastnameEn) {
        this.lastnameEn = lastnameEn;
    }

    public String getFirstnameUa() {
        return firstnameUa;
    }
    public void setFirstnameUa(String firstnameUa) {
        this.firstnameUa = firstnameUa;
    }

    public String getLastnameUa() {
        return lastnameUa;
    }
    public void setLastnameUa(String lastnameUa) {
        this.lastnameUa = lastnameUa;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
