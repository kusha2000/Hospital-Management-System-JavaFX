package com.kushan.hms.util;

import com.kushan.hms.db.Database;
import com.kushan.hms.dto.User;

public class Cookie {
    public static User selectedUser= Database.userTable.get(1);
}
