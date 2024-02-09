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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField EmpPassw_field;

    @FXML
    private TextField idEmp_field;

    @FXML
    private Button regEmp_button;

    @FXML
    private Button toMain_button;

    @FXML
    void initialize() {
        toMain_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toMain_button.getScene().getWindow();
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
        regEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
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
            try
            {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("regUser");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            String idEmp = idEmp_field.getText().trim();
            int id=Integer.parseInt(idEmp);

            String en = null;
            try {
                oos.writeInt(id);
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (en.equals("aliveUs")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Пользователь под таким логином уже существует!");
                alert.showAndWait();

            }
            else if(en.equals("notaliveUs")) {
                String passw = EmpPassw_field.getText().trim();

                try {

                    oos.writeUTF(passw);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String en1;
                try {
                    en1 = iis.readUTF();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (en1.equals("alive")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText(null);
                    alert.setContentText("Вы успешно зарегистрировали пользователя!");
                    alert.showAndWait();
                    Stage stage = (Stage) regEmp_button.getScene().getWindow();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load((getClass().getResource("admin-menu.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setScene(new Scene(root, 700, 400));
                    stage.setResizable(false);
                    stage.show();
                } else if (en1.equals("notalive")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Сотрудника под таким табельным номером нет!");
                    alert.showAndWait();
                }
                try {
                    iis.close();
                    sock.close();
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
