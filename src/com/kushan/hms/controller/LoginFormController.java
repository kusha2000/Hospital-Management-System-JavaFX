package com.kushan.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.kushan.hms.db.DBConnection;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.User;
import com.kushan.hms.enums.AccountType;
import com.kushan.hms.util.Cookie;
import com.kushan.hms.util.CrudUtil;
import com.kushan.hms.util.PasswordConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public JFXRadioButton rBtnPatient;
    public JFXPasswordField txtPassword;
    public AnchorPane loginContext;

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }

    public void signinOnAction(ActionEvent actionEvent) throws IOException {
        //=============================
        //For JavaFX manual Data Store
        //=============================

//        String email=txtEmail.getText();
//        String password=txtPassword.getText();
//        AccountType accountType=rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT;
//
//        for(User dto: Database.userTable){
//            if(dto.getEmail().trim().toLowerCase().equals(email)){
//                if(dto.getPassword().trim().toLowerCase().equals(password)){
//                    if(dto.getAccountType().equals(accountType)){
//                        new Alert(Alert.AlertType.CONFIRMATION,"Success!").show();
//                        setUi("DoctorDashboardForm");
//                    }else{
//                        new Alert(Alert.AlertType.WARNING,String.format("We can't find your %s Account",accountType.name())).show();
//                        return;
//                    }
//                }else{
//                    new Alert(Alert.AlertType.WARNING,"Your Password is incorrect").show();
//                    return;
//                }
//
//            }
//            new Alert(Alert.AlertType.WARNING,String.format("We can't find an email (%s)",email)).show();
//        }

        //=============================
        //For JavaFX using JDBC
        //=============================

        String email=txtEmail.getText();
        String password=txtPassword.getText();
        AccountType accountType=rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT;
        try {
            //JDBC
            // 1 driver load => dependency
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // 2 Create a Connection
            //Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalMS_javafx","root","");
            // 3 write a SQL
           // String sql="SELECT * FROM user WHERE email=? AND account_type=?";
            // 4 Create Statement
//            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
//            pstm.setString(1,email);
//            pstm.setString(2,accountType.name());
//            ResultSet resultSet=pstm.executeQuery();

            ResultSet resultSet= CrudUtil.execute("SELECT * FROM user WHERE email=? AND account_type=?",email,accountType.name());
            if(resultSet.next()){
                Cookie.selectedUser=new User(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),"",accountType );

                if(new PasswordConfig().decrypt(password,resultSet.getString("password"))){
                    if(accountType.equals(AccountType.PATIENT)){
                        ResultSet selectedPatientResult=CrudUtil.execute("SELECT patient_id FROM patient WHERE email=?",email);
                        if(selectedPatientResult.next()){
                            setUi("PatientDashboardForm");
                        }else{
                            setUi("PatientRegistrationForm");
                        }

                    }else{
                        ResultSet selectedDoctorResult=CrudUtil.execute("SELECT doctor_id FROM doctor WHERE email=?",email);
                        if(selectedDoctorResult.next()){
                            setUi("DoctorDashboardForm");
                        }else{
                            setUi("DoctorRegistrationForm");
                        }

                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Your Password is Wrong").show();

                }
            }else{
                new Alert(Alert.AlertType.WARNING,String.format("We can't find an email (%s)",email)).show();

            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage=(Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
