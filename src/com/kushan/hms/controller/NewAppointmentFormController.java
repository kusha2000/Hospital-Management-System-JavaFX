package com.kushan.hms.controller;

import com.jfoenix.controls.*;
import com.kushan.hms.util.Cookie;
import com.kushan.hms.util.CrudUtil;
import com.kushan.hms.util.IdGenerator;
import com.kushan.hms.view.tm.DoctorComboView;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class NewAppointmentFormController {
    public AnchorPane newAppointmentContext;
    public JFXComboBox<String> cmbDoctors;
    public JFXDatePicker txtDate;
    public JFXTimePicker txtTime;
    public JFXTextField txtAmount;
    public JFXTextArea txtMessage;
    String selectedDoctorId = "";
    String selectedPatientId = "";

    private ArrayList<DoctorComboView> viewList = new ArrayList<>();

    public void initialize() {
        setDoctorIds();
        setPatientData();
    }

    private void setPatientData() {
        try {
            ResultSet set = CrudUtil.execute("SELECT patient_id FROM patient WHERE email=?",
                    Cookie.selectedUser.getEmail());
            if (set.next()){
                selectedPatientId  = set.getString(1);
            }else{
                // => redirect patient registration
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void setDoctorIds() {
        try {
            ResultSet set = CrudUtil.execute("SELECT doctor_Id,first_name,last_name FROM doctor");
            ObservableList<String> obList = FXCollections.observableArrayList();
            int index = 1;
            while (set.next()) {
                DoctorComboView viewData = new DoctorComboView(index, set.getString(1),
                        set.getString(2) + " " + set.getString(3));
                viewList.add(viewData);
                obList.add(index + ". " + viewData.getName());
                index++;

            }
            cmbDoctors.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("PatientDashboardForm");
    }

    public void seeAvailabilityOnAction(ActionEvent actionEvent) {
        Optional<DoctorComboView> selectedRecord = viewList.stream().filter(
                e -> e.getIndex() == Integer.parseInt(cmbDoctors.getValue()
                        .split("\\.")[0])).findFirst();
        if (selectedRecord.isPresent()) {
            selectedDoctorId = selectedRecord.get().getDocId();
            //txtAmount.setText(new Random().nextInt(1000)+"");
            txtAmount.setText(String.valueOf(new Random().nextInt(1000)));
            //====>
        } else {
            System.out.println("Empty");
        }

    }

    public void submitDataOnAction(ActionEvent actionEvent) {

        String id = new IdGenerator().generateId(
                "SELECT appointment_id FROM appointment ORDER BY appointment_id DESC LIMIT 1",
                "A"

        );

        try {
            boolean isSaved = CrudUtil.execute("INSERT INTO appointment VALUES(?,?,?,?,?,?,?)",
                    id,txtDate.getValue(),txtTime.getValue(),
                    Double.parseDouble(txtAmount.getText()),false,
                    selectedPatientId,selectedDoctorId
            );
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Completed!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) newAppointmentContext.getScene().getWindow();
        System.out.println(stage);
        stage.setScene(new Scene(FXMLLoader.
                load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}