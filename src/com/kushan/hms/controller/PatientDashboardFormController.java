package com.kushan.hms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDashboardFormController {
    public Label lblDate;
    public AnchorPane patientDashboardContext;
    public Label lblTime;

    public void navigateToNewAppoinmentPage(ActionEvent actionEvent) throws IOException {
        setUi("NewAppointmentForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage=(Stage) patientDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
    public void logoutAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
}
