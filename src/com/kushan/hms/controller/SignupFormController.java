package com.kushan.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.UserDto;
import com.kushan.hms.enums.AccountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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

        String email=txtEmail.getText().trim().toLowerCase();

//        for(UserDto dto: Database.userTable){
//            if(dto.getEmail().trim().toLowerCase().equals(email)){
//                new Alert(Alert.AlertType.WARNING,"Your email is already Existed").show();
//                return;
//            }else{
//
//            }
//        }
        Optional<UserDto> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();

        if(selectedUser.isPresent()){
            new Alert(Alert.AlertType.WARNING,"email is already exists!").show();
            return;
        }
        Database.userTable.add(new UserDto(txtFirstName.getText(),txtLastName.getText(),email,txtPassword.getText(),rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT));
        new Alert(Alert.AlertType.CONFIRMATION,"Welcome!").show();
        setUi();
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi();
    }

    private void setUi() throws IOException {
        Stage stage=(Stage) registerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

}
