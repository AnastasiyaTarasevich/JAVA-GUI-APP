package com.example.kursach_3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Acc_table;
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
import com.example.kursach_3.Tables.Acc_table;
public class OutputAccController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    ObservableList<Acc_table> studList = FXCollections.observableArrayList();
    ObservableList<Acc_table> list;

    @FXML
    private TableView<Acc_table> table;
    @FXML
    private TableColumn<Acc_table, Integer> column_id;
    @FXML
    private TableColumn<Acc_table, String> column_login;

    @FXML
    private TableColumn<Acc_table, String> column_passw;

    @FXML
    private Button toAdminMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<Acc_table> getAcc() throws IOException{

        String ss ;
        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
            size = iis.readInt();
        int id;
        int col=0;
        for(int i=0;i<size;i++)
        {
            ss="";
            id=iis.readInt();

                int length=iis.readInt();
                String login="";
                for(int j=0;j<length;j++)
                {
                    login+=iis.readChar();
                }
                ss+=login+"";
                length=iis.readInt();
                String passw="";
                for(int j=0;j<length;j++)
                {
                    passw+=iis.readChar();
                }
                ss+=passw;
            list = FXCollections.observableArrayList(
                    new Acc_table(id,login,passw)
             );

            studList.add(list.get(0));
        }
        table.setItems(studList);
        return studList;

    }
    @FXML
    void initialize()
    {
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
        column_passw.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPassword()));
        try {
            table.setItems(getAcc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
