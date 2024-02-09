package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.event.ActionEvent;
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
//import jdk.internal.access.JavaIOFileDescriptorAccess;


public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
@FXML
private TextField login_field;
@FXML
private PasswordField passw_field;
    @FXML
    private Button toMain_button;
@FXML
private Button enterAdmin_button;
    @FXML
    void initialize() {
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


  enterAdmin_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

      String login = login_field.getText().trim();
      String password = passw_field.getText().trim();
      String addr = "127.0.0.1";
      Socket sock = null;
      DataOutputStream oos;
      DataInputStream iis;
      try {
          sock = new Socket(InetAddress.getByName(addr), 1024);
          oos = new DataOutputStream(sock.getOutputStream());
          iis = new DataInputStream(sock.getInputStream());
          oos.writeUTF("add");
          oos.writeUTF(login);
          oos.writeUTF(password);
          String str;
          System.out.println("login" + login);
          System.out.println("password" + password);
          str = iis.readUTF();
          if (str.equals("admin")) {
              Stage stage = (Stage) enterAdmin_button.getScene().getWindow();
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
          if (str.equals("noadmin")) {
              Shake adminlog = new Shake(login_field);
              Shake adminpassw = new Shake(passw_field);
              adminlog.playAnim();
              adminpassw.playAnim();
              Alert alert=new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Ошибка");
              alert.setHeaderText(null);
              alert.setContentText("Вы не зарегистрированы как администратор!");
              alert.showAndWait();
          }
          if (str.equals("wrap")) {
              Shake adminlog = new Shake(login_field);
              Shake adminpassw = new Shake(passw_field);
              adminlog.playAnim();
              adminpassw.playAnim();
          }
      } catch (IOException e) {
          throw new RuntimeException(e);
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
