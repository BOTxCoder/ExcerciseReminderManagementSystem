package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginScreenController {
    @FXML
    private TextField user;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    static String username;
    @FXML
    void setLogin(ActionEvent actionEvent) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exreciseremindermanagement", "root", "ani");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT userName , Password FROM exreciseremindermanagement.login where userName = ? and Password = ?;");
        username = user.getText();
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password.getText());
        ResultSet resultSet = preparedStatement.executeQuery();
        SwitchFXML switchFXML = new SwitchFXML();
        if(resultSet.next()){
          switchFXML.switchFxml("HomePage.fxml","Home",actionEvent);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Login Failed .. Retry");
            alert.showAndWait();
        }
    }
}
