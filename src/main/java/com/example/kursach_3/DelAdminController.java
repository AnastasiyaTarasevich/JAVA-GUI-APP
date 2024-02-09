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

public class DelAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterDelAdmin_button;

    @FXML
    private TextField login_field;

    @FXML
    private Button toAdmin_menu_button;

    @FXML
    void initialize() {
        toAdmin_menu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toAdmin_menu_button.getScene().getWindow();
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
        enterDelAdmin_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            String log = login_field.getText().trim();


         /*   String en;
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
                Stage stage =  (Stage) enterDelAdmin_button.getScene().getWindow();
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
            }
            else   if (en.equals("notalive"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого администратора в бд нет!");
                alert.showAndWait();
            }*/
        });

    }

}
