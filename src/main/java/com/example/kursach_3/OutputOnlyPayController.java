
package com.example.kursach_3;

        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.net.URL;
        import java.net.UnknownHostException;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

public class OutputOnlyPayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField date;


    @FXML
    private TextField income;

    @FXML
    private TextField last_name;

    @FXML
    private TextField name;

    @FXML
    private TextField oklad;

    @FXML
    private TextField premium;

    @FXML
    private TextField prepaym;

    @FXML
    private TextField pretax;

    @FXML
    private TextField social;

    @FXML
    private TextField surname;
    @FXML
    private Button toMain_button;

    @FXML
    private TextField total_sum;

    @FXML
    void initialize() throws IOException {
        String addr = "127.0.0.1";
        Socket sock = null;
        DataOutputStream oos;
        DataInputStream iis;
            sock = new Socket(InetAddress.getByName(addr), 1024);
            oos = new DataOutputStream(sock.getOutputStream());
            iis = new DataInputStream(sock.getInputStream());
     String sur= iis.readUTF();
     String nam= iis.readUTF();
     String secnam=  iis.readUTF();
     String dat=  iis.readUTF();
     double salar= iis.readDouble();
     double premCo= iis.readDouble();
     double prepay =iis.readDouble();
     double incom= iis.readDouble();
     double socia= iis.readDouble();
     double preta= iis.readDouble();
     double total_su= iis.readDouble();
     surname.setText(sur);
     name.setText(nam);
     last_name.setText(secnam);
     date.setText(dat);
     oklad.setText(Double.toString(salar));
     premium.setText(Double.toString(premCo));
     prepaym.setText(Double.toString(prepay));
     income.setText(Double.toString(incom));
     social.setText(Double.toString(socia));
     pretax.setText(Double.toString(preta));
     total_sum.setText(Double.toString(total_su));
        toMain_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) toMain_button.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load((getClass().getResource("user.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.show();
            System.out.println("Жмал назад");
        });

    }

}
