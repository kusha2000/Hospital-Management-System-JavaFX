package com.kushan.hms.util;

import com.kushan.hms.db.DBConnection;

import java.sql.*;

public class IdGenerator {
//    public int generateId(){
//        try{
////            String sql="SELECT user_id from user ORDER BY user_id DESC LIMIT 1";
////            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
////            ResultSet rst=pstm.executeQuery();
//
//            ResultSet rst=CrudUtil.execute("SELECT user_id from user ORDER BY user_id DESC LIMIT 1");
//            if(rst.next()){
//                //return rst.getInt("user_id")+1;
//                return rst.getInt(1)+1;
//            }
//        }catch (SQLException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        return 1;
//
//    }
    public String generateId(String sql, String prefix){
        try{
            ResultSet rst = CrudUtil.execute(sql);
            if (rst.next()){
                String tempId =  rst.getString(1);
                int id= Integer.parseInt(tempId.split("-")[1]); // -->
                id++;
                return prefix+"-"+id;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return prefix+"-1";
    }
}
