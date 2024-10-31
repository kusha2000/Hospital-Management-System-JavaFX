package com.kushan.hms.util;

import com.kushan.hms.db.DBConnection;

import java.sql.*;

public class IdGenerator {
    public int generateId(){
        try{
//            String sql="SELECT user_id from user ORDER BY user_id DESC LIMIT 1";
//            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet rst=pstm.executeQuery();

            ResultSet rst=CrudUtil.execute("SELECT user_id from user ORDER BY user_id DESC LIMIT 1");
            if(rst.next()){
                //return rst.getInt("user_id")+1;
                return rst.getInt(1)+1;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return 1;

    }
}
