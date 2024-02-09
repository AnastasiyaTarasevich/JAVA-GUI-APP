package com.example.kursach_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_3.Tables.Pay_table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GraphController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart pie_chart;
    ObservableList<PieChart.Data> payList = FXCollections.observableArrayList();
    @FXML
    private Button toAcc_menu_button;
    String addr = "127.0.0.1";
    Socket sock = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataInputStream iis;
        DataOutputStream oos;
        int size;
        try {
            sock = new Socket(InetAddress.getByName(addr), 1024);
            iis = new DataInputStream(sock.getInputStream());
            oos = new DataOutputStream(sock.getOutputStream());
            size = iis.readInt();
            ObservableList<PieChart.Data> list = null;
            for (int i = 0; i < size; i++) {
                String name = iis.readUTF();
                double salary = iis.readDouble();
                list = FXCollections.observableArrayList(
                        new PieChart.Data(name, salary)
                );
                payList.add(list.get(0));
            }

            pie_chart.setData(payList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        });

    }


}
