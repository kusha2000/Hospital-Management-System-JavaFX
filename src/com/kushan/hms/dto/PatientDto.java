package com.kushan.hms.dto;

import com.kushan.hms.enums.GenderType;

import java.util.Date;

public class PatientDto {
    private String nic;
    private String firstName;
    private String lastName;
    private Date dob;
    private GenderType genderType;
    private String address;
    private String email;

    public PatientDto() {
    }



    public PatientDto(String nic, String firstName, String lastName, Date dob, GenderType genderType, String address, String email) {
        this.setNic(nic);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDob(dob);
        this.setGenderType(genderType);
        this.setAddress(address);
        this.setEmail(email);
    }


    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
