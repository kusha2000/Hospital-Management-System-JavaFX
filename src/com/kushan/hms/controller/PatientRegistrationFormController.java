package com.kushan.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.User;
import com.kushan.hms.enums.GenderType;
import com.kushan.hms.util.Cookie;
import com.kushan.hms.util.CrudUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRegistrationFormController {
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtContact;
    public JFXRadioButton rBtnMale;
    public JFXRadioButton rBrnFemale;
    public TextArea txtAddress;
    public JFXButton btnSubmit;
    public AnchorPane patientRegistrationContext;
    public JFXDatePicker txtDob;

    public void initialize() {
        loadUserData();
    }
    private void loadUserData(){
        User selectedUser = Cookie.selectedUser;
        txtFirstName.setText(selectedUser.getFirstName());
        txtLastName.setText(selectedUser.getLastName());
        txtEmail.setText(selectedUser.getEmail());
    }
    private String generatePatientId() throws SQLException, ClassNotFoundException {
        ResultSet result= CrudUtil.execute("SELECT patient_id FROM patient ORDER BY patient_id DESC LIMIT 1");
        if(result.next()) {
            int lastId=Integer.parseInt(result.getString("patient_id").split("_")[1]);
            lastId++;
            return "P-"+lastId;
        }
        return new StringBuilder().append("P-1").toString();

    }

    public void submitDataOnAction(ActionEvent actionEvent) {
        try{
            String patientId=generatePatientId();
            boolean isSaved=CrudUtil.execute("INSERT INTO patient VALUES(?,?,?,?,?,?,?,?,?)",patientId,txtFirstName.getText(),txtLastName.getText(),txtEmail.getText(),txtContact.getText(),txtNic.getText(),txtAddress.getText(),txtDob.getValue(),rBtnMale.isSelected()? GenderType.MALE.name():GenderType.FEMALE.name());
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Welcome Patient...").show();
                setUi("PatientDashboardForm");
            }
        }catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage=(Stage) patientRegistrationContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
