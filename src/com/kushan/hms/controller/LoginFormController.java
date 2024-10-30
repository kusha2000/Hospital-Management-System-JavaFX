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

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public JFXRadioButton rBtnPatient;
    public JFXPasswordField txtPassword;
    public AnchorPane loginContext;

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpForm.fxml"))));
        stage.centerOnScreen();
    }

    public void signinOnAction(ActionEvent actionEvent) {
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        AccountType accountType=rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT;

        for(UserDto dto: Database.userTable){
            if(dto.getEmail().trim().toLowerCase().equals(email)){
                if(dto.getPassword().trim().toLowerCase().equals(password)){
                    if(dto.getAccountType().equals(accountType)){
                        new Alert(Alert.AlertType.CONFIRMATION,"Success!").show();
                        return;
                    }else{
                        new Alert(Alert.AlertType.WARNING,String.format("We can't find your %s Account",accountType.name())).show();
                        return;
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Your Password is incorrect").show();
                    return;
                }

            }
            new Alert(Alert.AlertType.WARNING,String.format("We can't find an email (%s)",email)).show();

        }
    }
}
