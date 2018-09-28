package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private TextArea username;

    @FXML
    private TextArea Date;

    @FXML
    private Button setReminder;

    @FXML
    private Button modifyReminder;

    @FXML
    private Button disableReminder;

    @FXML
    private Button DeleteReminder;

    @FXML
    private Button enableReminder;

    @FXML
    private Button viewYourReminders;

    @FXML
    private Button back;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(LoginScreenController.username);
        Date.setText(LocalDate.now().toString());
        SwitchFXML switchFXML = new SwitchFXML();
        setReminder.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("SetReminderScreen.fxml","Set Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        modifyReminder.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("ModifyRemainder.fxml","Modify Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        disableReminder.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("DisableRemainder.fxml","Disable Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        enableReminder.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("EnableRemainder.fxml","Enable Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        DeleteReminder.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("DeleteRemainder.fxml","Delete Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        viewYourReminders.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("ViewRemainder.fxml","View Reminder",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        back.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("LoginScreen.fxml","Login",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
