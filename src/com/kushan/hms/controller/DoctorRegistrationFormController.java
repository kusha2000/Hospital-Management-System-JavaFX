package com.kushan.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.DoctorDto;
import com.kushan.hms.dto.User;
import com.kushan.hms.enums.GenderType;
import com.kushan.hms.util.Cookie;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorRegistrationFormController {
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtSpecializations;
    public JFXRadioButton rBtnMale;
    public JFXRadioButton rBrnFemale;
    public TextArea txtAddress;
    public AnchorPane doctorRegistrationContext;
    public JFXButton btnSubmit;

    public void initialize() {
        loadUserData();

        txtNic.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Database.doctorTable.stream().filter(e->e.getNic().equals(newValue)).findAny().isPresent()) {
                new Alert(Alert.AlertType.WARNING,"NIC Conflicts").show();
                btnSubmit.setDisable(true);
                txtNic.setStyle("-fx-border-color: red");
                return;
            }
            btnSubmit.setDisable(false);
        });
    }
    private void loadUserData(){
        User selectedUser = Cookie.selectedUser;
        txtFirstName.setText(selectedUser.getFirstName());
        txtLastName.setText(selectedUser.getLastName());
        txtEmail.setText(selectedUser.getEmail());
    }
    public void submitDataOnAction(ActionEvent actionEvent) {
        DoctorDto doctorDto=new DoctorDto(
                txtFirstName.getText().trim(),
                txtLastName.getText().trim(),
                txtNic.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtSpecializations.getText(),
                txtAddress.getText(),
                rBtnMale.isSelected()? GenderType.MALE : GenderType.FEMALE
        );
        Database.doctorTable.add(doctorDto);
        Stage stage = (Stage) doctorRegistrationContext.getScene().getWindow();
        stage.close();
    }

}
