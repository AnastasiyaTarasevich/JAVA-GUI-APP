package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Admin_table;
import com.example.kursach_3.Tables.User_table;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OutputUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<User_table, Integer> column_id;

    @FXML
    private TableColumn<User_table, Integer> column_login;

    @FXML
    private TableColumn<User_table, String> column_passw;
    ObservableList<User_table> userList = FXCollections.observableArrayList();
    ObservableList<User_table> list;
    @FXML
    private TableView<User_table> table;

    @FXML
    private Button toAdminMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<User_table> getUser() throws IOException {

        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
        size = iis.readInt();
        int id,login;
        String  passw;
        int col=0;
        for(int i=0;i<size;i++)
        {

            id=iis.readInt();
            login= iis.readInt();
            passw= iis.readUTF();

            list = FXCollections.observableArrayList(
                    new User_table(id,login,passw)
            );

            userList.add(list.get(0));
        }
        table.setItems(userList);
        return userList;

    }

    @FXML
    void initialize() {
        toAdminMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage =  (Stage) toAdminMenu_button.getScene().getWindow();
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
        column_id.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdUser()));
        column_login.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdEmployee()));
        column_passw.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPassword()));
        try {
            table.setItems(getUser());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
