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

public class OutputMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OutputListAcc_button;

    @FXML
    private Button OutputListAdmin_button;

    @FXML
    private Button OutputListEmp_button;

    @FXML
    private Button toAdminMenu_button;

    @FXML
    void initialize() {

        toAdminMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toAdminMenu_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("admin-menu.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал назад");
        });
        OutputListAcc_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream oos;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("ListAcc");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) OutputListAcc_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("outputAcc.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });
        OutputListAdmin_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream oos;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("ListAdmin");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) OutputListAdmin_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("outputAdmin.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });
        OutputListEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream oos;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("ListUser");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) OutputListEmp_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("outputUser.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });


    }

}
