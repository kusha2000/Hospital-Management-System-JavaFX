package com.kushan.hms.controller;

import com.kushan.hms.db.Database;
import com.kushan.hms.dto.PatientDto;
import com.kushan.hms.view.tm.PatientTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

public class PatientManagementFormController {
    public TableView<PatientTm> tblPatients;
    public TableColumn colNic;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableColumn colEmail;
    public TextField txtSearch;
    public AnchorPane patientContext;
    public Label lblDate;

    public void initialize() {
        loadAllData("");

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllData(newValue);
        });

        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("genderType"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllData(String s) {
        s=s.toLowerCase();
        ObservableList<PatientTm> tmList= FXCollections.observableArrayList();
        for(PatientDto dto: Database.patientTable){
            if(dto.getFirstName().contains(s) || dto.getLastName().contains(s) || dto.getEmail().contains(s)){
                tmList.add(new PatientTm(dto.getEmail(),dto.getFirstName(),dto.getLastName(),new SimpleDateFormat("yyyy-MM-dd").format(dto.getDob()),dto.getGenderType(),dto.getAddress(),10, dto.getEmail()));
            }
        }
        tblPatients.setItems(tmList);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }
}
