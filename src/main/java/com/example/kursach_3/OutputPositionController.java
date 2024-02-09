package com.example.kursach_3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


import com.example.kursach_3.Tables.Acc_table;
import com.example.kursach_3.Tables.Position_table;
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

public class OutputPositionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Position_table, Integer> column_id;

    @FXML
    private TableColumn<Position_table, String> column_name;

    @FXML
    private TableColumn<Position_table, Double> column_salary;

    @FXML
    private TableView<Position_table> table;
    ObservableList<Position_table> PosList = FXCollections.observableArrayList();
    ObservableList<Position_table> list;
    @FXML
    private Button toAdminMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<Position_table> getPos() throws IOException{

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
            String name="";
            name=iis.readUTF();
            ss+=name+"";
            double salary;
           salary=iis.readDouble();

            list = FXCollections.observableArrayList(
                    new Position_table(id,name,salary)
            );

            PosList.add(list.get(0));
        }
        table.setItems(PosList);
        return PosList;

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
        column_name.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getName()));
        column_salary.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSalary()));
        try {
            table.setItems(getPos());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
