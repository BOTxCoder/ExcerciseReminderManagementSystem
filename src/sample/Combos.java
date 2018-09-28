package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Combos {

   static ObservableList<String> setSubjectCombo() {
        ObservableList<String> observableList1 = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "ani");
            PreparedStatement preparedStatement = connection.prepareStatement("select  from bookmoviedetails;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                observableList1.add(resultSet.getString("name"));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
       return observableList1;
    }

}
