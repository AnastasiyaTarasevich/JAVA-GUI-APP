package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Acc_table;
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

public class OutputEmpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    ObservableList<Emp_table> empList = FXCollections.observableArrayList();
    ObservableList<Emp_table> list;

    @FXML
    private TableColumn<Emp_table, Integer> column_id;

    @FXML
    private TableColumn<Emp_table, Integer> column_idPosition;

    @FXML
    private TableColumn<Emp_table, String> column_name;

    @FXML
    private TableColumn<Emp_table, String> column_secname;

    @FXML
    private TableColumn<Emp_table, String> column_surname;

    @FXML
    private TableView<Emp_table> table;

    @FXML
    private Button toAdminMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;
    private ObservableList<Emp_table> getEmp() throws IOException{

        String ss ;
        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
        size = iis.readInt();
        int id;
        int idPos;
        String surname,name,secname;
        int col=0;
        for(int i=0;i<size;i++)
        {
            ss="";
            id=iis.readInt();
            idPos=iis.readInt();
            surname=iis.readUTF();
            name=iis.readUTF();
            secname=iis.readUTF();


            list = FXCollections.observableArrayList(
                    new Emp_table(id,idPos,surname,name,secname)
            );

            empList.add(list.get(0));
        }
        table.setItems(empList);
        return empList;

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
        column_id.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdEmp()));
        column_idPosition.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdPos()));
        column_name.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        column_surname.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSurname()));
        column_secname.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSecname()));

        try {
            table.setItems(getEmp());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
