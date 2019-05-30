package clinic.ui.View;

import clinic.generalclasses.Employee;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import clinic.generalclasses.EmployeeSalaryDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeeSalaryDetailController implements Initializable {

    @FXML
    private TableColumn<EmployeeSalaryDetail, Integer> employeeSalaryDetailEIDCol;

    @FXML
    private TableColumn<EmployeeSalaryDetail, Date> employeeSalaryDetailDateCol;

    @FXML
    private TableColumn<EmployeeSalaryDetail, Float> employeeSalaryDetailAmountCol;

    @FXML
    private JFXButton okEmployeeSalaryDetail;

    @FXML
    private TableView<EmployeeSalaryDetail> allEmployeeSalaryDetail;

    @FXML
    private TableColumn<EmployeeSalaryDetail, Integer> employeeSalaryDetailESIDCol;

    @FXML
    void okEmployeeSalaryDetailActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProperties();
        fillForm();
    }

    public void setProperties() {
        employeeSalaryDetailESIDCol.setCellValueFactory(new PropertyValueFactory<>("esid"));
        employeeSalaryDetailAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        employeeSalaryDetailEIDCol.setCellValueFactory(new PropertyValueFactory<>("eid"));
        employeeSalaryDetailDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    /*this.esid = esid;
     this.amount = amount;
     this.date = date;
     this.eid = eid;*/

    public void fillForm() {
        try {
            Employee emp = Employee.e;
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select * from tbl_Employee_Salary_Detail where EID=" + emp.getId());
            Integer ESID = 0;
            Double amount = 0.0;
            Date d = null;
            Integer EID = 0;
            /*(Integer esid, Float amount, Date date, Integer eid)*/
            while (rs.next()) {
                ESID = rs.getInt("ESID");
                amount = Double.parseDouble(rs.getString("Amount"));
                d = rs.getDate("Date");
                EID = rs.getInt("EID");
                System.out.println(ESID);
                allEmployeeSalaryDetail.getItems().add(new EmployeeSalaryDetail(ESID, amount, d, EID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSalaryDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
