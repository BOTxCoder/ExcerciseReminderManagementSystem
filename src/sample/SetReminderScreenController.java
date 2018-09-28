package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetReminderScreenController implements Initializable {
    @FXML
    private javafx.scene.control.DatePicker DatePicker;

    @FXML
    private ComboBox<String> subjects;

    @FXML
    private TextArea description;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField Number;

    @FXML
    private TextField smsNumber;

    @FXML
    private RadioButton sevenDays;

    @FXML
    private RadioButton threeDays;

    @FXML
    private RadioButton fiveDays;

    @FXML
    private Button back;

    @FXML
    private Button confirm;
    private RadioButton selected;
    private  String membershipDuration ;
    ObservableList<String> observableList1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SwitchFXML switchFXML = new SwitchFXML();
        back.setOnAction((ActionEvent actionEvent)->{
            try {
                switchFXML.switchFxml("HomePage.fxml","Home",actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        observableList1.addAll("DeadLift","Squads","BendOver","Shavasan","Padmasan");
        subjects.setItems(observableList1);
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        DatePicker.setDayCellFactory(dayCellFactory);
    }
    @FXML
    private void setToggleAction(){
        ToggleGroup group = new ToggleGroup();
        sevenDays.setToggleGroup(group);
        fiveDays.setToggleGroup(group);
        threeDays.setToggleGroup(group);
        selected = (RadioButton) group.getSelectedToggle();
        membershipDuration = selected.getId();
    }
    @FXML
    void validate(){
        if(DatePicker.getEditor().getText().isEmpty()| subjects.getSelectionModel().isEmpty()
        |description.getText().isEmpty()|emailAddress.getText().isEmpty()|
        Number.getText().isEmpty()|smsNumber.getText().isEmpty()|!(sevenDays.isSelected()|fiveDays.isSelected()|threeDays.isSelected()
        )){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("All fields are mandatory.");
            alert.showAndWait();
        }

    }
    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);


                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
    @FXML
    void setConfirm() throws SQLException {
        validate();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exreciseremindermanagement", "root", "ani");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reminder(date1,subject1,Description,Email,ContactNumber,SmsNumber,nextRecurrence) values (?,?,?,?,?,?,?);");
        preparedStatement.setDate(1, Date.valueOf(Date.valueOf(DatePicker.getValue()).toLocalDate()));
        preparedStatement.setString(2,subjects.getSelectionModel().getSelectedItem());
        preparedStatement.setString(3,description.getText());
        preparedStatement.setString(4, emailAddress.getText());
        preparedStatement.setString(5, Number.getText());
        preparedStatement.setString(6, smsNumber.getText());
        preparedStatement.setString(7,membershipDuration);
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Reminder Set");
        alert.showAndWait();
    }
}
