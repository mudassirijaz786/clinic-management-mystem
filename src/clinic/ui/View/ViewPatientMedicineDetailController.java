package clinic.ui.View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import clinic.generalclasses.PatientMedicineDetail;
import clinic.generalclasses.Patient;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewPatientMedicineDetailController implements Initializable {

    @FXML
    private TableColumn<PatientMedicineDetail, Integer> patientMedicineMIDCol;

    @FXML
    private TableColumn<PatientMedicineDetail, Integer> patientMedicineQuantityCol;

    @FXML
    private TableColumn<PatientMedicineDetail, Integer> patientMedicinePIDCol;

    @FXML
    private JFXTextField searchPatientMedicine;

    @FXML
    private TableColumn<PatientMedicineDetail, String> patientMedicineMNameCol;
    @FXML
    private TableView<PatientMedicineDetail> allPatientMedicineTable;
    @FXML
    private JFXButton okPatientMedicine;

    @FXML
    private TableColumn<PatientMedicineDetail, String> patientMedicinePNameCol;

    public void setProperties() {
        patientMedicinePIDCol.setCellValueFactory(new PropertyValueFactory<>("PID"));
        patientMedicineMIDCol.setCellValueFactory(new PropertyValueFactory<>("MID"));
        patientMedicinePNameCol.setCellValueFactory(new PropertyValueFactory<>("PName"));
        patientMedicineMNameCol.setCellValueFactory(new PropertyValueFactory<>("MName"));
        patientMedicineQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

    }

    @FXML
    void okPatientMedicineActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
//        stage.hide();
    }

    @FXML
    void searchPatientMedicineActionPerformed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProperties();
        Patient p = Patient.p;
        try {

            ResultSet rs = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=NGO_Clinic;integratedSecurity=true;").createStatement().executeQuery("select * from tbl_Medicine_Patient where PID=" + p.getId());
            Integer mid = 0;
            String mName = "";
            Integer mquantity = 0;
            System.out.println(p);
            while (rs.next()) {
                mid = rs.getInt("MID");
                mquantity = rs.getInt("Quantity");
                mName = findName(mid);
                allPatientMedicineTable.getItems().add(new PatientMedicineDetail(p.getId(), mid, p.getName(), mName, mquantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findName(Integer mid) {
        try {
            ResultSet res = DatabaseUtil.Database.statement.executeQuery("select Name from tbl_Medicine where MID=" + mid);
            String mName = "";
            while (res.next()) {
                mName = res.getString("Name");
            }
            return mName;
        } catch (SQLException ex) {
            Logger.getLogger(ViewPatientMedicineDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
