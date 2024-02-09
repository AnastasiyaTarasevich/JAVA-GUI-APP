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

public class InputPayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField date_field;

    @FXML
    private TextField hours_field;

    @FXML
    private TextField idEmp_field;

    @FXML
    private TextField idPrem_field;

    @FXML
    private TextField prepaym_field;

    @FXML
    private Button toAccMenu_button;

    @FXML
    private Button toInputPay_button;

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
        toInputPay_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
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
                oos.writeUTF("inputPay");
                iis = new DataInputStream(sock.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String idEmp = idEmp_field.getText().trim();
            try {
                if (Integer.parseInt(idEmp) == Integer.parseInt(idEmp)) ;
                //flag = false;

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
            int idE=Integer.parseInt(idEmp);
            String idPrem = idPrem_field.getText().trim();
            int idP=Integer.parseInt(idPrem);
            String d=date_field.getText().trim();
          /*  SimpleDateFormat format=new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            try {
                Date date=format.parse(d);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }*/
            String prep=prepaym_field.getText().trim();
            double prepaym=0;
            try{
                if(Double.parseDouble(prep)==Double.parseDouble(prep));
                 prepaym=Double.parseDouble(prep);
            }
            catch(Exception exception)
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Вы ввели буквы, где должны были быть числа!!!");
                alert.showAndWait();
            }
            int hours=0;
            String h=hours_field.getText().trim();
            try{
                if(Integer.parseInt(h)==Integer.parseInt(h));
                 hours=Integer.parseInt(h);
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
                oos.writeInt(idE);
                oos.writeInt(idP);
                oos.writeUTF(d);
                oos.writeDouble(prepaym);
                oos.writeInt(hours);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
             String en,en2;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
          if (en.equals("notalive"))
            {
                //alert
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого сотрудника в бд нет!");
                alert.showAndWait();
                try {
                    iis.close();
                    oos.close();
                    sock.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

           else if (en.equals("alive")) {
              try {
                  en2 = iis.readUTF();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }

              if (en2.equals("notpremlive")) {
                      System.out.println("Таккой премии нет");
                      Alert alert=new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Ошибка");
                      alert.setHeaderText(null);
                      alert.setContentText("Такой премии в бд нет!");
                      alert.showAndWait();
                  }
              if (en2.equals("premalive")) {
                  System.out.println("Такая премия есть");
                  String en3;
                  try {
                      en3 = iis.readUTF();
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }
                  if (en3.equals("ok"))
                  {
                      Alert alert=new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Успех");
                      alert.setHeaderText(null);
                      alert.setContentText("Операция прошла успешно!");
                      alert.showAndWait();
                      Stage stage =  (Stage) toInputPay_button.getScene().getWindow();
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
              }
              }

        });

    }

}
