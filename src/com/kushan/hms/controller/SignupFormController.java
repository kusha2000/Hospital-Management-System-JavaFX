package com.kushan.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.kushan.hms.db.DBConnection;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.User;
import com.kushan.hms.enums.AccountType;
import com.kushan.hms.util.CrudUtil;
import com.kushan.hms.util.IdGenerator;
import com.kushan.hms.util.IdGeneratorRegister;
import com.kushan.hms.util.PasswordConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class SignupFormController {
    public JFXTextField txtEmail;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public JFXRadioButton rBtnPatient;
    public JFXPasswordField txtPassword;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public AnchorPane registerContext;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {

        //=============================
        //For JavaFX manual Data Store
        //=============================

//        String email = txtEmail.getText().trim().toLowerCase();
//
////        for(UserDto dto: Database.userTable){
////            if(dto.getEmail().trim().toLowerCase().equals(email)){
////                new Alert(Alert.AlertType.WARNING,"Your email is already Existed").show();
////                return;
////            }else{
////
////            }
////        }
//        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
//
//        if (selectedUser.isPresent()) {
//            new Alert(Alert.AlertType.WARNING, "email is already exists!").show();
//            return;
//        }
//        Database.userTable.add(new User(txtFirstName.getText(), txtLastName.getText(), email, txtPassword.getText(), rBtnDoctor.isSelected() ? AccountType.DOCTOR : AccountType.PATIENT));
//        new Alert(Alert.AlertType.CONFIRMATION, "Welcome!").show();
//        setUi("LoginForm");

        //=============================
        //For JavaFX using JDBC
        //=============================

        User user = new User(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText().trim().toLowerCase(), new PasswordConfig().encrypt(txtPassword.getText()), rBtnDoctor.isSelected() ? AccountType.DOCTOR : AccountType.PATIENT);
        try {
            //JDBC
            // 1 driver load => dependency
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // 2 Create a Connection
            //Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalMS_javafx","root","");
            // 3 write a SQL
            //String sql="INSERT INTO user VALUES (?,?,?,?,?,?)";
            // 4 Create Statement
//            PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
//            pstm.setInt(1,new IdGenerator().generateId());
//            pstm.setString(2,user.getFirstName());
//            pstm.setString(3,user.getLastName());
//            pstm.setString(4,user.getEmail());
//            pstm.setString(5,user.getPassword());
//            pstm.setString(6,user.getAccountType().name());
//            // 5 Execute
//            int isSaved=pstm.executeUpdate();
            String id = new IdGeneratorRegister().generateId(user.getAccountType().name());
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO user VALUES (?,?,?,?,?,?)",id
                    ,user.getFirstName(),user.getLastName(),user.getEmail(),
                    user.getPassword(),user.getAccountType().name()
            );
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                setUi("LoginForm");
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!").show();
            }
            //====================
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage=(Stage) registerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

}
