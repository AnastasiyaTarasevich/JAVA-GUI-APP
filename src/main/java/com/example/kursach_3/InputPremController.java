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

public class InputPremController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField col_prem;

    @FXML
    private Button inputPrem_button;

    @FXML
    private TextField name_prem;

    @FXML
    private Button toAccMenu_button;

    @FXML
    void initialize() {
        toAccMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
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
        inputPrem_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
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
                oos.writeUTF("inputPrem");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String name = name_prem.getText().trim();
            String sum = col_prem.getText().trim();
            double col = 0;
            try{
                if(Double.parseDouble(sum)==Double.parseDouble(sum));
                 col=Double.parseDouble(sum);
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
                oos.writeDouble(col);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String en;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (en.equals("4"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Операция прошла успешно!");
                alert.showAndWait();
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
