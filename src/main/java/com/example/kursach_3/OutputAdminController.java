package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Acc_table;
import com.example.kursach_3.Tables.Admin_table;
import com.example.kursach_3.Tables.Emp_table;
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

public class OutputAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    ObservableList<Admin_table> adminList = FXCollections.observableArrayList();
    ObservableList<Admin_table> list;
    @FXML
    private TableColumn<Admin_table, Integer> column_id;

    @FXML
    private TableColumn<Admin_table, String> column_login;

    @FXML
    private TableColumn<Admin_table, String> column_passw;

    @FXML
    private TableView<Admin_table> table;

    @FXML
    private Button toAdminMenu_button;

    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<Admin_table> getAdmin() throws IOException {

        String ss ;
        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
        size = iis.readInt();
        int id;
        String login, passw;
        int col=0;
        for(int i=0;i<size;i++)
        {

            id=iis.readInt();
            login= iis.readUTF();
            passw= iis.readUTF();

            list = FXCollections.observableArrayList(
                    new Admin_table(id,login,passw)
            );

            adminList.add(list.get(0));
        }
        table.setItems(adminList);
        return adminList;

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
        column_id.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getId()));
        column_login.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getLogin()));
        column_passw.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPassw()));
        try {
            table.setItems(getAdmin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
