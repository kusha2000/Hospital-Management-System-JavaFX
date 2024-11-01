package com.kushan.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.kushan.hms.db.Database;
import com.kushan.hms.dto.DoctorDto;
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
    private String generateDoctorId() throws SQLException, ClassNotFoundException {
        ResultSet result= CrudUtil.execute("SELECT doctor_id FROM doctor ORDER BY doctor_id DESC LIMIT 1");
        if(result.next()) {
            String selectedId=result.getString(1);
            String[] splitData=selectedId.split("-");
            String splitId=splitData[1];
            int id=Integer.parseInt(splitId);
            id++;
            return "D-"+id;
        }
        return "D-1";

    }
    public void submitDataOnAction(ActionEvent actionEvent) {
        //=============================
        //For JavaFX manual Data Store
        //=============================

//        DoctorDto doctorDto=new DoctorDto(
//                txtFirstName.getText().trim(),
//                txtLastName.getText().trim(),
//                txtNic.getText(),
//                txtContact.getText(),
//                txtEmail.getText(),
//                txtSpecializations.getText(),
//                txtAddress.getText(),
//                rBtnMale.isSelected()? GenderType.MALE : GenderType.FEMALE
//        );
//        Database.doctorTable.add(doctorDto);
//        Stage stage = (Stage) doctorRegistrationContext.getScene().getWindow();
//        stage.close();

        //=============================
        //For JavaFX using JDBC
        //=============================

        try{
            String docId=generateDoctorId();
            boolean isSaved=CrudUtil.execute("INSERT INTO doctor VALUES(?,?,?,?,?,?,?,?,?)",docId,txtFirstName.getText(),txtLastName.getText(),txtContact.getText(),txtEmail.getText(),txtNic.getText(),txtSpecializations.getText(),txtAddress.getText(),rBtnMale.isSelected()?GenderType.MALE.name():GenderType.FEMALE.name());
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Welcome Doctor...").show();
                setUi("DoctorDashboardForm");
            }
        }catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }

    }
    private void setUi(String location) throws IOException {
        Stage stage=(Stage) doctorRegistrationContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

}
