package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DeleteRemainderController implements Initializable {
    @FXML
    private javafx.scene.control.DatePicker DatePicker;

    @FXML
    private ComboBox<String> subjects;

    @FXML
    private ComboBox<String> remainder;

    @FXML
    private TextArea description;

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
        observableList.addAll("DeadLift","Squads","BendOver","Shavasan","Padmasan");
        subjects.setItems(observableList);
        remainder.setItems(observableList);
    }
    @FXML
    void validate(){
        if(DatePicker.getEditor().getText().isEmpty() | subjects.getSelectionModel().isEmpty()|
                remainder.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("All Fields are Manadtory");
            alert.showAndWait();
        }
    }
    @FXML
    void setConfirm() throws SQLException {
        validate();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exreciseremindermanagement", "root", "ani");
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM exreciseremindermanagement.reminder WHERE date1 = ? and subject1 =?;");
        LocalDate date = DatePicker.getValue();
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setString(2,subjects.getSelectionModel().getSelectedItem());
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Remainder Disabled");
        alert.showAndWait();
    }

}
