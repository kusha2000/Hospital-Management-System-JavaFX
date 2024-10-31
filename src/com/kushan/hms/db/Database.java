package com.kushan.hms.db;

import com.kushan.hms.dto.DoctorDto;
import com.kushan.hms.dto.PatientDto;
import com.kushan.hms.dto.User;
import com.kushan.hms.enums.AccountType;
import com.kushan.hms.enums.GenderType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable= new ArrayList();
    public static ArrayList<DoctorDto> doctorTable= new ArrayList();
    public static ArrayList<PatientDto> patientTable= new ArrayList();

    static{
        userTable.add(new User("Kushan","Andarawewa","kushan@gmail.com","1234", AccountType.PATIENT));
        userTable.add(new User("Nimal","Perera","nimal@gmail.com","1234", AccountType.DOCTOR));


        doctorTable.add(new DoctorDto("Nimal","Perera","9876","+94715628223","nimal@gmail.com","Sample 1","Colombo", GenderType.MALE));

        try{
            patientTable.add(new PatientDto("123","Amila","Sandaruwan",new SimpleDateFormat("yyyy-MM-dd").parse("2000-03-08"),GenderType.MALE,"Colombo","amila@gmail.com"));
            patientTable.add(new PatientDto("124","Bimali","Wijesiri",new SimpleDateFormat("yyyy-MM-dd").parse("1995-07-18"),GenderType.FEMALE,"Galle","bimali@gmail.com"));
            patientTable.add(new PatientDto("125","Pawan","karunarathna",new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-14"),GenderType.MALE,"Kandy","pawan@gmail.com"));
            patientTable.add(new PatientDto("126","Kasuni","Perera",new SimpleDateFormat("yyyy-MM-dd").parse("1998-12-28"),GenderType.FEMALE,"Kurunegala","kasuni@gmail.com"));
            patientTable.add(new PatientDto("127","Dinal","Silva",new SimpleDateFormat("yyyy-MM-dd").parse("1994-02-10"),GenderType.MALE,"Gampha","dinal@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
