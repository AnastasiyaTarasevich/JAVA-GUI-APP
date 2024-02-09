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

public class RedactUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterRedactUser_button;

    @FXML
    private TextField idEmp_field;

    @FXML
    private Button toAdmin_menu_button;

    @FXML
    void initialize() {
        toAdmin_menu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
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
        enterRedactUser_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent->
        {
            String log = idEmp_field.getText().trim();
            int idEmp=Integer.parseInt(log);
            String addr = "127.0.0.1";
            Socket sock = null;
            DataOutputStream oos;
            DataInputStream iis;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("RedactUser");
                iis = new DataInputStream(sock.getInputStream());
                oos.writeInt(idEmp);
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
                Stage stage =  (Stage) enterRedactUser_button.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load((getClass().getResource("redactUser2.fxml")));
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
            else   if (en.equals("notalive"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого пользователяв бд нет!");
                alert.showAndWait();
                try {
                    oos.close();
                    iis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }  );

    }

}
