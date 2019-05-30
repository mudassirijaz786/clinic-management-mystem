package clinic.ui.screens;

import clinic.generalclasses.Record;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RecordsController implements Initializable {

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton addRecords;

    @FXML
    private TableView<clinic.generalclasses.Record> allRecordsTable;

    @FXML
    private JFXButton removeRecords;

    @FXML
    private JFXButton modifyRecords;
    @FXML
    private TableColumn<clinic.generalclasses.Record, Integer> recordAppointmentIDCol;
    @FXML
    private TableColumn<clinic.generalclasses.Record, Integer> recordPatientIDCol;

    @FXML
    private TableColumn<clinic.generalclasses.Record, Integer> recordIDCol;
    @FXML
    private TableColumn<clinic.generalclasses.Record, Integer> recordRoomCol;
    @FXML
    private TableColumn<clinic.generalclasses.Record, Date> recordAppointmentDateCol;

    public void setProperties() {
        recordAppointmentDateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        recordAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentNo"));
        recordPatientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        recordIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        recordRoomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
    }

    @FXML
    void searchActionPerformed(KeyEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Record where RID like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allRecordsTable.getItems().clear();
                allRecordsTable.refresh();
                for (; rs.next();) {
                    Record r = new Record(rs.getInt("RID"), rs.getDate("AppointmentDate"), rs.getInt("AppointmentNo"), rs.getInt("Room"), rs.getInt("PID"));
                    allRecordsTable.getItems().add(r);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            fillTable();
        }
    }

    public void fillTable() {
        try {
            String sql = "select * from tbl_Record";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allRecordsTable.getItems().clear();
            allRecordsTable.refresh();
            for (; rs.next();) {
                Record r = new Record(rs.getInt("RID"), rs.getDate("AppointmentDate"), rs.getInt("AppointmentNo"), rs.getInt("Room"), rs.getInt("PID"));
                allRecordsTable.getItems().add(r);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    void addRecordsActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddRecords.fxml"));
            Stage dashboardAdmin = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardAdmin.setScene(scene);
            dashboardAdmin.showAndWait();
            fillTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifyRecordsActionPerformed(ActionEvent event) {
        try {
            if (allRecordsTable.getSelectionModel().getSelectedItems() == null) {
                JOptionPane.showMessageDialog(null, "select a record to modify");
            } else {
                clinic.generalclasses.Record.r = allRecordsTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyRecords.fxml"));
                Stage dashboardAdmin = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardAdmin.setScene(scene);
                dashboardAdmin.showAndWait();
                fillTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeRecordsActionPerformed(ActionEvent event) {
        clinic.generalclasses.Record r = allRecordsTable.getSelectionModel().getSelectedItem();
        if (r == null) {
            JOptionPane.showMessageDialog(null, "Select a record to Remove");
        } else {
            try {
                String sql = "delete from tbl_Record where RID=" + r.getId();
                DatabaseUtil.Database.statement.execute(sql);
                fillTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    void validate(KeyEvent event) {
        String c = event.getCharacter();
        if ("0123456789".contains(c)); else {
            event.consume();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setProperties();
        fillTable();
    }

}
