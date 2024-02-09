package com.example.kursach_3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;



public class HelloController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button rg_button;

    @FXML
    private Button accountant_button;

    @FXML
    private Button admin_button;

    @FXML
    private Button user_button;

    @FXML
    void initialize() throws IOException {

        admin_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            Stage stage =  (Stage) admin_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("admin.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал на админа");
        });
        accountant_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            Stage stage =  (Stage) accountant_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("account.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал на бухгалтера");
        });

        user_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            Stage stage =  (Stage) user_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("user.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал на сотрудника");

        });
        rg_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            Stage stage =  (Stage) rg_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("signUp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал на регистрацию");

        });
    }

}
