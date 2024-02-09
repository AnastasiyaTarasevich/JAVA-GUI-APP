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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputPositionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button inputPosition_button;

    @FXML
    private TextField name_position;

    @FXML
    private TextField salary;

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
        inputPosition_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream oos;
            DataInputStream iis;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("inpPos");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String name = name_position.getText().trim();
            String sum = salary.getText().trim();
            double sum1=0;
            try{
                if(Double.parseDouble(sum)==Double.parseDouble(sum));
                 sum1 = Double.parseDouble(sum);
            }
            catch(Exception exception)
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Вы ввели буквы, где должны были быть числа!!!");
                alert.showAndWait();
            }
            try {
                oos.writeUTF(name);
                oos.writeDouble(sum1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String en;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (en.equals("3"))
            {
                Stage stage =  (Stage) inputPosition_button.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load((getClass().getResource("success.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(new Scene(root, 700, 400));
                stage.setResizable(false);
                stage.show();
            }
            try {
                iis.close();
                sock.close();
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
