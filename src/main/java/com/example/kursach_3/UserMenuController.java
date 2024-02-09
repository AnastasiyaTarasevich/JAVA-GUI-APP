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

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admintoMain_button;

    @FXML
    private Button outputPay_button;
    @FXML
    private TextField IdEmp;

    @FXML
    void initialize()
    {

        admintoMain_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) admintoMain_button.getScene().getWindow();
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
            System.out.println("Жмал назад");
        });
        outputPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            String i= IdEmp.getText().trim();
            int id=Integer.parseInt(i);
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("OutputOnlyPay");
                oos.writeInt(id);
                iis = new DataInputStream(sock.getInputStream());
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
                Stage stage = (Stage) outputPay_button.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load((getClass().getResource("outputOnlyPay.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(new Scene(root, 700, 400));
                stage.setResizable(false);
                stage.show();
                try {
                    oos.close();
                    iis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            else   if (en.equals("notalive")) {

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого расчетного листа в бд нет!");
                alert.showAndWait();
            }


        });


    }

}
