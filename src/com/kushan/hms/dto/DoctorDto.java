package com.kushan.hms.dto;

import com.kushan.hms.enums.GenderType;

public class DoctorDto {
    private String fName;
    private String lname;
    private String nic;
    private String contact;
    private String email;
    private String specializations;
    private String address;

    public DoctorDto(String fName, String lname, String nic, String contact, String email, String specializations, String address, GenderType gender) {
        this.fName = fName;
        this.lname = lname;
        this.nic = nic;
        this.contact = contact;
        this.email = email;
        this.specializations = specializations;
        this.address = address;
        this.gender = gender;
    }

    private GenderType gender;



    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
