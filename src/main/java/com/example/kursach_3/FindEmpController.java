package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Emp_table;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FindEmpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enteridEmp_button;

    @FXML
    private TextField idEmp_field;

    @FXML
    private TableColumn<Emp_table, Integer> idPos_column;

    @FXML
    private TableColumn<Emp_table, Integer> id_column;

    @FXML
    private TableColumn<Emp_table, String> name_column;
    @FXML
    private TableView<Emp_table> table;

    @FXML
    private TableColumn<Emp_table, String> sec_column;

    @FXML
    private TableColumn<Emp_table, String> sur_column;
    ObservableList<Emp_table> empList = FXCollections.observableArrayList();
    ObservableList<Emp_table> list;
    @FXML
    private Button toAcc_menu_button;
    String addr = "127.0.0.1";
    Socket sock = null;
    DataOutputStream oos;
    DataInputStream iis;
    private ObservableList<Emp_table> getEmp1() throws IOException{

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
        for(int i=0;i<size;i++) {
            id = iis.readInt();
            idPos = iis.readInt();
            surname = iis.readUTF();
            name = iis.readUTF();
            secname = iis.readUTF();
            list = FXCollections.observableArrayList(
                    new Emp_table(id, idPos, surname, name, secname)
            );

            empList.add(list.get(0));
        }
        table.setItems(empList);
        return empList;

    }
    @FXML
    void initialize() {


       toAcc_menu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           Stage stage =  (Stage) toAcc_menu_button.getScene().getWindow();
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
        id_column.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdEmp()));
        idPos_column.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdPos()));
        name_column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        sur_column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSurname()));
        sec_column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSecname()));
        enteridEmp_button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->
        {
          String s =  idEmp_field.getText().trim();


            try {
                sock = new Socket(InetAddress.getByName(addr), 1024);
                oos = new DataOutputStream(sock.getOutputStream());
                oos.writeUTF("FindEmp");
                iis = new DataInputStream(sock.getInputStream());
                oos.writeUTF(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String en;
            try {
                en = iis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(en.equals("wrap"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого сотрудника в бд нет!");
                alert.showAndWait();
            }
            if(en.equals("alive"))
            {

                try {
                    table.setItems(getEmp1());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    oos.close();
                    iis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else   if (en.equals("notalive"))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такого сотрудника в бд нет!");
                alert.showAndWait();
            }
        });


    }

}
