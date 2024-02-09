package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Prem_table;
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

public class OutputPremController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Prem_table, Integer> column_id;

    @FXML
    private TableColumn<Prem_table, String> column_name;

    @FXML
    private TableColumn<Prem_table, Double> column_sum;

    @FXML
    private TableView<Prem_table> table;
    ObservableList<Prem_table> premList = FXCollections.observableArrayList();
    ObservableList<Prem_table> list;

    @FXML
    private Button toAccMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<Prem_table> getPrem() throws IOException {

        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
        size = iis.readInt();
        int id;
        String  name;
        double col;
        for(int i=0;i<size;i++)
        {

            id=iis.readInt();
            name= iis.readUTF();
            col= iis.readDouble();

            list = FXCollections.observableArrayList(
                    new Prem_table(id,name,col)
            );

            premList.add(list.get(0));
        }
        table.setItems(premList);
        return premList;

    }

    @FXML
    void initialize() {
        toAccMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
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
        column_id.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getId()));
        column_name.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getName()));
        column_sum.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSum()));
        try {
            table.setItems(getPrem());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
