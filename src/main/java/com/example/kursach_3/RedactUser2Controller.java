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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RedactUser2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField newpassw;

    @FXML
    private Button redactUser_button;

    @FXML
    private Button toAdminmenu_button;

    @FXML
    void initialize() {
        redactUser_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                    String passw = newpassw.getText().trim();
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
                        iis = new DataInputStream(sock.getInputStream());
                        oos.writeUTF(passw);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        toAdminmenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
        {
            Stage stage =  (Stage) toAdminmenu_button.getScene().getWindow();
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

    }

}
