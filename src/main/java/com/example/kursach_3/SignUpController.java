package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import animations.Shake;
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
import java.net.*;



public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg_button;

    @FXML
    private TextField signUp_login;

    @FXML
    private PasswordField signUp_passw;

    @FXML
    private Button toMain_button;

    @FXML
    void initialize() throws RuntimeException{
        reg_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent->
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
                oos.writeUTF("reg");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String log = signUp_login.getText().trim();
            String passw = signUp_passw.getText().trim();


            try {
                oos.writeUTF(log);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String en = null;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (en.equals("alive")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Админ под таким логином уже существует!");
                    alert.showAndWait();

                }
           else if (en.equals("notalive"))
                 {
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
                     if (en1.equals("1")) {
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("Успех");
                         alert.setHeaderText(null);
                         alert.setContentText("Вы успешно зарегистрировались!");
                         alert.showAndWait();
                         Stage stage = (Stage) reg_button.getScene().getWindow();
                         Parent root = null;
                         try {
                             root = FXMLLoader.load((getClass().getResource("hello-view.fxml")));
                         } catch (IOException e) {
                             throw new RuntimeException(e);
                         }
                         stage.setScene(new Scene(root, 700, 400));
                         stage.setResizable(false);
                         stage.show();
                     }
                    else if(en1.equals("wrap"))
                     {
                         Shake adminlog = new Shake(signUp_login);
                         Shake adminpassw = new Shake(signUp_passw);
                         adminlog.playAnim();
                         adminpassw.playAnim();
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


        toMain_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage =  (Stage) toMain_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("hello-view.fxml")));
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
