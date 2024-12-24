package com.kushan.hms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGeneratorRegister {
    public String generateId(String userType) {
        try {
            String sql = "SELECT user_id FROM user WHERE account_type='" + userType + "' ORDER BY user_id DESC LIMIT 1";
            ResultSet rst = CrudUtil.execute(sql);
            if (rst.next()) {
                String tempId = rst.getString("user_id");
                int id = Integer.parseInt(tempId.split("-")[1]);
                id++;
                if (userType.equals("PATIENT")) {
                    return "P-" + id;
                } else if (userType.equals("DOCTOR")) {
                    return "D-" + id;
                } else {
                    throw new IllegalArgumentException("Invalid userType: " + userType);
                }
            } else {
                // Handle the case where no ID exists in the database yet
                if (userType.equals("PATIENT")) {
                    return "P-1";
                } else if (userType.equals("DOCTOR")) {
                    return "D-1";
                } else {
                    throw new IllegalArgumentException("Invalid userType: " + userType);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Return null or handle exception as appropriate
        }
    }
}
