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

public class EnableRemainderController implements Initializable {
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
    ObservableList<String> observableList = FXCollections.observableArrayList();

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
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        DatePicker.setDayCellFactory(dayCellFactory);
    }

    @FXML
    void setDatePicker() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exreciseremindermanagement", "root", "ani");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT subject1,Description,Email,ContactNumber,SmsNumber FROM exreciseremindermanagement.reminder where date1 = ?;");
        LocalDate date = DatePicker.getValue();
        preparedStatement.setDate(1, Date.valueOf(date));
        ResultSet resultSet = preparedStatement.executeQuery();
        subjects.setItems(observableList);
        while(resultSet.next()){
            observableList.addAll(resultSet.getString("subject1"));
            description.setText(resultSet.getString("Description"));
            emailAddress.setText(resultSet.getString("Email"));
            Number.setText(resultSet.getString("ContactNumber"));
            smsNumber.setText(resultSet.getString("SmsNumber"));
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
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exreciseremindermanagement", "root", "ani");
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  exreciseremindermanagement.reminder SET Enable = 1 WHERE Date = ? and Subject =?;");
        LocalDate date = DatePicker.getValue();
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setString(2,subjects.getSelectionModel().getSelectedItem());
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Remainder Disabled");
        alert.showAndWait();
    }
}
