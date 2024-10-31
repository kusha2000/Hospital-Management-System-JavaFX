package com.kushan.hms.util;

import com.kushan.hms.db.Database;
import com.kushan.hms.dto.UserDto;

public class Cookie {
    public static UserDto selectedUser= Database.userTable.get(1);
}
