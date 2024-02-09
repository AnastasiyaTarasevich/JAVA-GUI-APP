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

public class DelPayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button DelPay_button;

    @FXML
    private TextField idEmp;

    @FXML
    private Button toAdminmenu_button;

    @FXML
    void initialize() {
        toAdminmenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toAdminmenu_button.getScene().getWindow();
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
        DelPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String id1 = idEmp.getText().trim();
            int id=Integer.parseInt(id1);
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("DelPay");
                iis = new DataInputStream(sock.getInputStream());
                oos.writeInt(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String en;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(en.equals("alive"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Операция прошла успешно!");
                alert.showAndWait();
                Stage stage =  (Stage) DelPay_button.getScene().getWindow();
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
            else   if (en.equals("notalive"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого расчетного листа в бд нет!");
                alert.showAndWait();
            }
        });

    }

}
