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

public class InputEmpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_field;
    @FXML
    private Button toInputEmp_button;
    @FXML
    private TextField name_field;

    @FXML
    private TextField secname_field;

    @FXML
    private TextField surname_field;

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
        toInputEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            boolean flag = true;
            String id = null;
            DataOutputStream oos;
            Socket sock = null;
            while (flag) {
                String addr = "127.0.0.1";
                sock = null;
                try {
                    sock = new Socket(InetAddress.getByName(addr), 1024);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oos = null;
                DataInputStream iis = null;
                try {
                    oos = new DataOutputStream(sock.getOutputStream());
                    oos.writeUTF("InputEmp");
                    
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                id = id_field.getText().trim();

                try {
                    if (Integer.parseInt(id) == Integer.parseInt(id)) ;
                    flag = false;

                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Вы ввели буквы, где должны были быть числа!!!");
                    alert.showAndWait();
                    try {
                        oos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            int ID = Integer.parseInt(id);
            String name = name_field.getText().trim();
            String surname = surname_field.getText().trim();
            String secname = secname_field.getText().trim();
            DataInputStream iis;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                oos.writeInt(ID);
                oos.writeUTF(name);
                oos.writeUTF(surname);
                oos.writeUTF(secname);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String en;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (en.equals("alive")) {
                Stage stage = (Stage) toInputEmp_button.getScene().getWindow();
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
            if (en.equals("notalive")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такой должности в базе данных нет");
                alert.showAndWait();
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
