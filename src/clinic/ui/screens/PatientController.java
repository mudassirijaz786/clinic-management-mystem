package clinic.ui.screens;

import clinic.generalclasses.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PatientController implements Initializable {

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton removePatient;
    @FXML
    private JFXButton addPatient;
    @FXML
    private AnchorPane patientContent;
    @FXML
    private JFXButton viewMedicineDetail;
    @FXML
    private JFXButton modifyPatient;

    @FXML
    private TableColumn<clinic.generalclasses.Patient, String> patientAddressCol;
    @FXML
    private TableColumn<clinic.generalclasses.Patient, String> patientGenderCol;
    @FXML
    private TableColumn<clinic.generalclasses.Patient, Integer> patientIDCol;
    @FXML
//    private TableColumn<clinic.generalclasses.Patient, Integer> patientMIDCol;
//    @FXML
    private TableView<clinic.generalclasses.Patient> allPatientsTable;
    @FXML
    private TableColumn<clinic.generalclasses.Patient, String> patientAgeCol;
    @FXML
    private TableColumn<clinic.generalclasses.Patient, String> patientContactNoCol;
    @FXML
    private TableColumn<clinic.generalclasses.Patient, String> patientNameCol;

    public void setProperties() {
        patientIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //patientMIDCol.setCellValueFactory(new PropertyValueFactory<>("mid"));
//        patientMIDCol.setCellValueFactory(new PropertyValueFactory<>("mid"));
        patientContactNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        patientGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patientAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        patientAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    @FXML
    void searchActionPerformed(KeyEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Patient where Name like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allPatientsTable.getItems().clear();
                allPatientsTable.refresh();
                for (; rs.next();) {

                    Patient p = new Patient(rs.getInt("PID"), rs.getString("Name"), rs.getString("Contact"), rs.getString("Gender"), rs.getString("Address"), rs.getInt("Age"));
                    allPatientsTable.getItems().add(p);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            fillTable();
        }
    }

    @FXML
    void viewMedicineDetailActionPerformed(ActionEvent event) {
        try {
            if (allPatientsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select a patient to View Medicine Detail");
            } else {

                clinic.generalclasses.Patient.setP(allPatientsTable.getSelectionModel().getSelectedItem());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/View/ViewPatientMedicineDetail.fxml"));
                Stage dashboardAdmin = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardAdmin.setScene(scene);
                dashboardAdmin.showAndWait();
//                fillTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addPatientActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddPatient.fxml"));
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
    void modifyPatientActionPerformed(ActionEvent event) {
        try {
            if (allPatientsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select a customer to modify");
            } else {
                clinic.generalclasses.Patient.setP(allPatientsTable.getSelectionModel().getSelectedItem());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyPatient.fxml"));
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
    void removePatientActionPerformed(ActionEvent event) {
        clinic.generalclasses.Patient p = allPatientsTable.getSelectionModel().getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(null, "Select a Patient to Remove");
        } else {

            int res = JOptionPane.showConfirmDialog(null, "Sure to Delete");
//            System.out.println(res);
            if (res == 0) {
                try {
                    String sql = "delete from tbl_Patient where PID=" + p.getId();
                    DatabaseUtil.Database.statement.execute(sql);
                    fillTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
            };
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setProperties();
//        allPatientsTable.getItems().add(new Patient(1 ,"G","T", 341, "g", "g", 12));
        fillTable();

    }

    public void fillTable() {
        try {
            String sql = "select * from tbl_Patient";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allPatientsTable.getItems().clear();
            allPatientsTable.refresh();
            for (; rs.next();) {

                Patient p = new Patient(rs.getInt("PID"), rs.getString("Name"), rs.getString("Contact"), rs.getString("Gender"), rs.getString("Address"), rs.getInt("Age"));
//                p.setMid(i);
                allPatientsTable.getItems().add(p);
                //System.out.println(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
