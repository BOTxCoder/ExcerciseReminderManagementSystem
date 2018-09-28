package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ViewRemainderController {
    @FXML
    private Button back;

    @FXML
    private Button modifyRemainder;

    @FXML
    private Button DisableRemainder;

    @FXML
    private Button DeleteRemainder;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private ComboBox<?> subject;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> ReminderName;

    @FXML
    private TableColumn<?, ?> ReminderSubject;

    @FXML
    private TableColumn<?, ?> ReminderDescription;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> contact;

    @FXML
    private TableColumn<?, ?> SMS;

    @FXML
    private TableColumn<?, ?> Status;

    @FXML
    private TableColumn<?, ?> RecurrenceFrequency;
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();


}