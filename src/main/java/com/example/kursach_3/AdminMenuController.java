package com.example.kursach_3;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminDelEmp_button;

    @FXML
    private Button adminDelUsers_button;

    @FXML
    private Button adminInputEmp_button;

    @FXML
    private Button adminOutputEmp_button;

    @FXML
    private Button adminOutputUsers_button;

    @FXML
    private Button adminRedactEmp_button;

    @FXML
    private Button adminRedactUsers_buton;

    @FXML
    private Button adminregUser_button;

    @FXML
    private Button adminPosition_button;
    @FXML
    private Button admintoMain_button;

    @FXML
    void initialize()
    {
        admintoMain_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage =  (Stage) admintoMain_button.getScene().getWindow();
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
        });
        adminregUser_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                    Stage stage =  (Stage) admintoMain_button.getScene().getWindow();
                    stage.close();
                    Parent root = null;
                    try {
                      //  root = FXMLLoader.load((getClass().getResource("reg-acc.fxml")));
                        root=FXMLLoader.load((getClass().getResource("reg-menu.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setScene(new Scene(root, 700, 400));
                    stage.setResizable(false);
                    stage.show();
                }
                );
        adminOutputUsers_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            Stage stage =  (Stage) adminOutputUsers_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("output-menu.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        adminRedactUsers_buton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                    Stage stage =  (Stage) adminRedactUsers_buton.getScene().getWindow();
                    stage.close();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load((getClass().getResource("redact-menu.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setScene(new Scene(root, 700, 400));
                    stage.setResizable(false);
                    stage.show();
                }
        );

        adminDelUsers_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            Stage stage =  (Stage) adminRedactUsers_buton.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("del-menu.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        adminInputEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->//to do
        {
            Stage stage =  (Stage) admintoMain_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("input-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });
        adminPosition_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->

        {
            Stage stage =  (Stage) admintoMain_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("position-menu.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        adminOutputEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            String addr = "127.0.0.1";
            Socket sock = null;
            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream oos;
            try {
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("ListEmp");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =  (Stage) admintoMain_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("output-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
        });
        adminRedactEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            Stage stage =  (Stage) adminRedactEmp_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("redact-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });
        adminDelEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
            Stage stage =  (Stage) adminRedactEmp_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("del-emp.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();

        });

    }

}
