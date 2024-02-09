package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Admin_table;
import com.example.kursach_3.Tables.Pay_table;
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

public class OutputPayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Pay_table, Integer> column_hours;

    @FXML
    private TableColumn<Pay_table, Integer> column_idEmp;

    @FXML
    private TableColumn<Pay_table, Integer> column_idPay;

    @FXML
    private TableColumn<Pay_table, Integer> column_idPrem;
    ObservableList<Pay_table> payList = FXCollections.observableArrayList();
    ObservableList<Pay_table> list;
    @FXML
    private TableColumn<Pay_table, String> column_income;

    @FXML
    private TableColumn<Pay_table, String> column_period;

    @FXML
    private TableColumn<Pay_table,Double> column_prepaym;

    @FXML
    private TableColumn<Pay_table,String> column_pretax;

    @FXML
    private TableColumn<Pay_table, String> column_social;

    @FXML
    private TableColumn<Pay_table, String> column_total;

    @FXML
    private TableView<Pay_table> table;

    @FXML
    private Button toAdminMenu_button;
    String addr = "127.0.0.1";
    Socket sock = null;

    private ObservableList<Pay_table> getPay() throws IOException {

        String ss ;
        DataInputStream iis;
        DataOutputStream oos;
        int size;
        sock = new Socket(InetAddress.getByName(addr), 1024);
        iis = new DataInputStream(sock.getInputStream());
        oos=new DataOutputStream(sock.getOutputStream());
        size = iis.readInt();
        int idPay,idEmp,idPrem,hours;
        String date;
        double income,prepaym,social,pretax,total_sum;
        for(int i=0;i<size;i++)
        {

            idPay=iis.readInt();
            idEmp=iis.readInt();
            idPrem=iis.readInt();
            date=iis.readUTF();
            hours=iis.readInt();
            income=iis.readDouble();
            social=iis.readDouble();
            prepaym=iis.readDouble();
            pretax=iis.readDouble();
            total_sum=iis.readDouble();
            String pretaxresult=String.format("%.2f",pretax);
            String socialresult=String.format("%.2f",social);
            String incomeresult=String.format("%.2f",income);
            String totalresult=String.format("%.2f",total_sum);
            list = FXCollections.observableArrayList(
                    new Pay_table(idPay,idEmp,idPrem,date,hours,prepaym,incomeresult,socialresult,pretaxresult,totalresult)
            );

            payList.add(list.get(0));
        }
        table.setItems(payList);
        return payList;

    }

    @FXML
    void initialize() {
        toAdminMenu_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage =  (Stage) toAdminMenu_button.getScene().getWindow();
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
        column_idEmp.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdEmp()));
        column_idPay.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getIdPay()));
        column_idPrem.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getIdPrem()));
        column_period.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPeriod()));
        column_hours.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getHours()));
        column_prepaym.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPrepayment()));
        column_income.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getIncome_tax()));
        column_social.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSocial_tax()));
        column_pretax.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPretax_sum()));
        column_total.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTotal_sum()));
        try {
            table.setItems(getPay());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
