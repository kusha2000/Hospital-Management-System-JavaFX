package com.kushan.hms.db;

import com.kushan.hms.dto.UserDto;
import com.kushan.hms.enums.AccountType;

import java.util.ArrayList;

public class Database {
    public static ArrayList<UserDto> userTable= new ArrayList();

    static{
        userTable.add(new UserDto("Kushan","Andarawewa","kushan@gmail.com","1234", AccountType.PATIENT));
        userTable.add(new UserDto("Nimal","Perera","nimal@gmail.com","1234", AccountType.DOCTOR));
    }
}
