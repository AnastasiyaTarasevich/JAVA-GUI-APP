package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SalaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button DelPay_button;

    @FXML
    private Button OutputPay_button;

    @FXML
    private Button SortPos_button;

    @FXML
    private Button findEmp_button;

    @FXML
    private Button inputPay_button;

    @FXML
    private Button sortSur_button;

    @FXML
    private Button toAccMenu_button;

    @FXML
    void initialize() {
        toAccMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage =  (Stage) toAccMenu_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("accountant-menu.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал назад");
        });

        findEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toAccMenu_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("find-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 761, 437));
            stage.setResizable(false);
            stage.show();
        });
        sortSur_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("SortSur");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) sortSur_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("sortSur-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        SortPos_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("SortPos");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) SortPos_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("sortPos-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        inputPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("InputPay");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) inputPay_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("inputPay.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        OutputPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("OutputPay");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) OutputPay_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("OutputPay.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 841, 458));
            stage.setResizable(false);
            stage.show();
        });
        DelPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) DelPay_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("DelPay.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
    }

}
