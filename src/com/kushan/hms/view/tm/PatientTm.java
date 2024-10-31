package com.kushan.hms.view.tm;

import com.kushan.hms.enums.GenderType;

import java.util.Date;

public class PatientTm {
    private String nic;
    private String firstName;
    private String lastName;
    private String dob;
    private GenderType genderType;
    private String address;
    private int age;
    private String email;

    public PatientTm() {
    }



    public PatientTm(String nic, String firstName, String lastName, String dob, GenderType genderType, String address, int age, String email) {
        this.setNic(nic);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDob(dob);
        this.setGenderType(genderType);
        this.setAddress(address);
        this.setAge(age);
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
